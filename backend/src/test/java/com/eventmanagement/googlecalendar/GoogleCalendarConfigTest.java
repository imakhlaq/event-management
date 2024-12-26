package com.eventmanagement.googlecalendar;

import com.eventmanagement.repository.IUserRepo;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class GoogleCalendarConfigTest {

    private GoogleCalendarConfig googleCalendarConfig;

    @Mock
    private OAuth2AuthorizedClient mockClient;

    @Mock
    private OAuth2AccessToken mockAccessToken;

    @Mock
    private IUserRepo mockUserRepo;

    private final JsonFactory mockJsonFactory = GsonFactory.getDefaultInstance();
    private final HttpTransport mockHttpTransport = mock(HttpTransport.class);
    private final String mockClientId = "mockClientId";
    private final String mockClientSecret = "mockClientSecret";
    private final String mockRefreshToken = "mockRefreshToken";
    private final String mockApplicationName = "mockAppName";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        googleCalendarConfig = new GoogleCalendarConfig(
            mockUserRepo,
            mockApplicationName,
            mockClientId,
            mockClientSecret,
            mockRefreshToken
        );
    }

    @Test
    void testGetCalendar() throws GeneralSecurityException, IOException {
        // Mock OAuth2AuthorizedClient behavior
        when(mockClient.getAccessToken()).thenReturn(mockAccessToken);
        when(mockAccessToken.getTokenValue()).thenReturn("mockTokenValue");

        // Execute the method under test
        Calendar result = googleCalendarConfig.getCalendar(mockClient);

        // Validate results
        assertNotNull(result);
        verify(mockClient, times(1)).getAccessToken();
        verify(mockAccessToken, times(1)).getTokenValue();
    }
}