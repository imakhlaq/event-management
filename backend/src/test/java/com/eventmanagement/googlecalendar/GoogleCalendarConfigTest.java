package com.eventmanagement.googlecalendar;

import com.eventmanagement.models.User;
import com.google.api.client.auth.oauth2.Credential;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.eventmanagement.repository.IUserRepo;
import com.eventmanagement.exception.custom.NoUserFoundException;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.services.calendar.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class GoogleCalendarConfigTest {

    @Mock
    private IUserRepo userRepo;

    @Mock
    private Credential.Builder credentialBuilder;

    @Mock
    private OAuth2AuthorizedClient client;

    @Mock
    private Credential credential;

    @Mock
    private OAuth2AccessToken oAuth2AccessToken;

    @InjectMocks
    private GoogleCalendarConfig googleCalendarConfig;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        googleCalendarConfig = new GoogleCalendarConfig(userRepo, "mock_app");
    }

    @Test
    void testGetCalendar_UserNotFound_ThrowsNoUserFoundException() throws GeneralSecurityException, IOException {
        // Arrange
        when(client.getPrincipalName()).thenReturn("nonexistent_user");
        when(userRepo.findUserByUsername("nonexistent_user")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoUserFoundException.class, () -> googleCalendarConfig.getCalendar(client));
        verify(userRepo, times(1)).findUserByUsername("nonexistent_user");
    }

    @Test
    void testGetCalendar_ValidUser_ReturnsCalendarInstance() throws GeneralSecurityException, IOException {
        // Arrange
        var mockUser = mock(User.class);
        when(client.getPrincipalName()).thenReturn("test_user");
        when(oAuth2AccessToken.getTokenValue()).thenReturn("test_toke");
        when(client.getAccessToken()).thenReturn(oAuth2AccessToken);
        when(userRepo.findUserByUsername("test_user")).thenReturn(Optional.of(mockUser));

        // Mock the HTTP_TRANSPORT and TOKEN_SERVER_URL
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        String tokenServerUrl = "https://oauth2.googleapis.com/token";

        // Act
        Calendar calendar = googleCalendarConfig.getCalendar(client);

        // Assert
        assertNotNull(calendar);
        assertEquals("mock_name", calendar.getApplicationName());
        verify(userRepo, times(1)).findUserByUsername("test_user");
    }

    @Test
    void testSaveRefreshTokenOnFirstRequest_ValidRefreshToken_SavesToken() {
        // Arrange
        var mockUser = mock(User.class);
        when(client.getPrincipalName()).thenReturn("test_user");
        when(client.getRefreshToken()).thenReturn(new OAuth2RefreshToken("mokedtoke", Instant.now()));
        when(userRepo.findUserByUsername("test_user")).thenReturn(Optional.of(mockUser));

        // Act
        googleCalendarConfig.saveRefreshTokenOnFirstRequest(client);

        // Assert
        verify(userRepo, times(1)).save(mockUser);
        verify(mockUser, times(1)).setRefreshToken("mockRefreshToken".getBytes());
    }

    @Test
    void testSaveRefreshTokenOnFirstRequest_NoRefreshToken_DoesNothing() {
        // Arrange
        when(client.getRefreshToken()).thenReturn(null);

        // Act
        googleCalendarConfig.saveRefreshTokenOnFirstRequest(client);

        // Assert
        verify(userRepo, never()).save(any());
    }
}