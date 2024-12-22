package com.eventmanagement.googlecalendar;

import com.eventmanagement.auth.repository.IUserRepo;
import com.eventmanagement.exception.custom.NoRefreshTokenException;
import com.eventmanagement.utils.TokenUtils;
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

import static com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants.TOKEN_SERVER_URL;

@Component
@Slf4j
public class GoogleCalendarConfig {

    private final String APPLICATION_NAME;
    private final IUserRepo userRepo;
    private final TokenUtils tokenUtils;
    //Global instance of the JSON factory.
    private final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    @Value("${client-id}")
    private String clientId;
    @Value("${client-secret}")
    private String clientSecret;

    public GoogleCalendarConfig(IUserRepo userRepo, @Value("${app.name}") String appName, TokenUtils tokenUtils) {
        this.userRepo = userRepo;
        this.APPLICATION_NAME = appName;
        this.tokenUtils = tokenUtils;
    }

    public Calendar getCalendar(OAuth2AuthorizedClient client) throws GeneralSecurityException, IOException {
        HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        var user = userRepo.findUserByUsername(client.getPrincipalName());
        if (!user.isPresent()) throw new NoRefreshTokenException("/", HttpStatus.BAD_REQUEST, "No Refresh Token Exits");

        // Build the Credential with the Builder
        Credential builder = new Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
            .setJsonFactory(JSON_FACTORY)
            .setTransport(HTTP_TRANSPORT)
            .setTokenServerUrl(new GenericUrl(TOKEN_SERVER_URL))
            .setClientAuthentication(new ClientParametersAuthentication(clientId, clientSecret))
            .build()
            .setAccessToken(client.getAccessToken().getTokenValue())
            .setRefreshToken(user.get().getRefreshToken());

        log.info("Connecting to the Google Calender Server");

        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, builder)
            .setApplicationName(APPLICATION_NAME)
            .build();
    }
}
