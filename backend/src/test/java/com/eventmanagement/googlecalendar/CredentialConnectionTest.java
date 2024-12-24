package com.eventmanagement.googlecalendar;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants.TOKEN_SERVER_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CredentialConnectionTest {

    private static final Logger log = LoggerFactory.getLogger(CredentialConnectionTest.class);

    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
    HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

    public CredentialConnectionTest() throws GeneralSecurityException, IOException {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Value("${test.property}")
    private String testProperty;

    @Test
    public void testProperty() {
        assertEquals("testValue", testProperty);
    }

    @Test
    void testGetCalendar() throws Exception {
        // Act
        Credential result = new Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
            .setJsonFactory(jsonFactory)
            .setTransport(HTTP_TRANSPORT)
            .setTokenServerUrl(new GenericUrl(TOKEN_SERVER_URL))
            .setClientAuthentication(new ClientParametersAuthentication("test_clientId", "client_secret"))
            .build()
            .setAccessToken("access_token")
            .setRefreshToken("refresh_token");

        // Assert
        assertNotNull(result);
    }
}