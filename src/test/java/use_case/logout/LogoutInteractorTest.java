package use_case.logout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogoutInteractorTest {

    LogoutUserDataAccessInterface mockUserRepository;
    LogoutOutputBoundary mockPresenter;

    @BeforeEach
    void setUp() {
        // Create the mock user repository
        mockUserRepository = mock(LogoutUserDataAccessInterface.class);
        mockPresenter = mock(LogoutOutputBoundary.class);
    }

    @Test
    void successTest() {
        LogoutInputData inputData = new LogoutInputData("Paul");

        doNothing().when(mockPresenter).prepareSuccessView(any(LogoutOutputData.class));
        doNothing().when(mockPresenter).prepareFailView(anyString());

        // This creates a successPresenter that tests whether the test case is as we expect.
        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData user) {
                // check that the output data contains the username of who logged out
                assertEquals("Paul", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LogoutInputBoundary interactor = new LogoutInteractor(mockUserRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureTest_UsernameNotFound() {
        // Test case where username is null (failure case)
        LogoutInputData inputData = new LogoutInputData(null);

        // Execute the logout process
        LogoutInputBoundary interactor = new LogoutInteractor(mockUserRepository, mockPresenter);
        interactor.execute(inputData);

        verify(mockPresenter).prepareFailView("Username not found.");
        verify(mockUserRepository, never()).setCurrentUsername(any());
    }

}