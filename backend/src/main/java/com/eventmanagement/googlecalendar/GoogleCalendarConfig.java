package com.eventmanagement.googlecalendar;

import com.eventmanagement.auth.repository.IUserRepo;
import com.eventmanagement.exception.custom.NoRefreshTokenException;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
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

@Component
@Slf4j
public class GoogleCalendarConfig {

    private final String APPLICATION_NAME;
    private final IUserRepo userRepo;
    //Global instance of the JSON factory.
    private final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public GoogleCalendarConfig(IUserRepo userRepo, @Value("${app.name}") String appName) {
        this.userRepo = userRepo;
        this.APPLICATION_NAME = appName;
    }

    private Calendar getCalendar(OAuth2AuthorizedClient client) throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod());
        credential.setAccessToken(client.getAccessToken().getTokenValue());

        var user = userRepo.findUserByUsername(client.getPrincipalName());
        if (!user.isPresent()) throw new NoRefreshTokenException("/", HttpStatus.BAD_REQUEST, "No Refresh Token Exits");

        credential.setRefreshToken(user.get().getRefreshToken());

        log.debug("Connecting to the Google Calender Server");

        return new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME)
            .build();
    }
}
