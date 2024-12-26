package com.eventmanagement.googlecalendar;

import com.eventmanagement.exception.custom.NoRefreshTokenException;
import com.eventmanagement.models.User;
import com.eventmanagement.repository.IUserRepo;
import com.eventmanagement.exception.custom.NoUserFoundException;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;

import static com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants.TOKEN_SERVER_URL;

@Component
@Slf4j
public class GoogleCalendarConfig {

    private final String APPLICATION_NAME;
    private final IUserRepo userRepo;
    //Global instance of the JSON factory.
    private final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    @Value("${client-id}")
    private String clientId;
    @Value("${client-secret}")
    private String clientSecret;
    @Value("${refresh-token}")
    private String refreshToken;

    public GoogleCalendarConfig(IUserRepo userRepo, @Value("${app.name}") String appName) {
        this.userRepo = userRepo;
        this.APPLICATION_NAME = appName;
    }

    /**
     * Creates a new calendar object needed to interact with the Google Calendar API.
     *
     * @param client The OAuth2AuthorizedClient object.
     * @return The Calendar object.
     */
    public Calendar getCalendar(OAuth2AuthorizedClient client) throws GeneralSecurityException, IOException {

        HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

/*        //Use this code when you have a db that persist the token
        var user = this.userRepo.findUserByUsername(client.getPrincipalName());
        if (user.isEmpty()) throw new NoUserFoundException(HttpStatus.BAD_REQUEST, "User Doesn't exists in DB");

        User userWithRefreshToken = null;//NOTE when you will use a db that persist the toke it will be available in user object

        if (user.get().getRefreshToken() == null) {
            //to extract refresh token and persist it on first request. Because google sends refresh_token only on first request
            userWithRefreshToken = saveRefreshTokenOnFirstRequest(client, user.get());
        } else {
            userWithRefreshToken = user.get();
        }*/

        // Build the Credential with the Builder
        Credential builder = new Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
            .setJsonFactory(JSON_FACTORY)
            .setTransport(HTTP_TRANSPORT)
            .setTokenServerUrl(new GenericUrl(TOKEN_SERVER_URL))
            .setClientAuthentication(new ClientParametersAuthentication(clientId, clientSecret))
            .build()
            .setAccessToken(client.getAccessToken().getTokenValue())
            .setRefreshToken(this.refreshToken);//ALERT change this when you have that can persist refresh token.

        log.info("Connecting to the Google Calender Server");

        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, builder)
            .setApplicationName(APPLICATION_NAME)
            .build();
    }

    /**
     * Retrieves the refresh token from the OAuth2AuthorizedClient and saves it to the user object.
     *
     * @param client The OAuth2AuthorizedClient object.
     * @return The user object with the refresh token saved.
     */
    private User saveRefreshTokenOnFirstRequest(OAuth2AuthorizedClient client, User user) {

        var ref_token = client.getRefreshToken();
        if (ref_token == null)
            throw new NoRefreshTokenException(HttpStatus.BAD_REQUEST, "Refresh Token for this user is not available");

        log.info("Saving refresh token {} with user", ref_token);
        user.setRefreshToken(ref_token.getTokenValue().getBytes());
        this.userRepo.save(user);
        return user;
    }
}
