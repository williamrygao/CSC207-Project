package use_case.signup;

import entity.user.CommonUserFactory;
import entity.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignupInteractorTest {

    SignupUserDataAccessInterface mockUserRepository;

    @BeforeEach
    void setUp() {
        // Create the mock user repository
        mockUserRepository = mock(SignupUserDataAccessInterface.class);
    }

    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "password");

        when(mockUserRepository.existsByName("Paul")).thenReturn(false);

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Paul", user.getUsername());
                when(mockUserRepository.existsByName("Paul")).thenReturn(true);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(mockUserRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);

        verify(mockUserRepository).existsByName("Paul");
        verify(mockUserRepository).save(any(User.class));
    }

    @Test
    void failurePasswordMismatchTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");

        when(mockUserRepository.existsByName("Paul")).thenReturn(false);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(mockUserRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);

        verify(mockUserRepository).existsByName("Paul");
    }

    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");

        when(mockUserRepository.existsByName("Paul")).thenReturn(true);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(mockUserRepository, failurePresenter, new CommonUserFactory());
        interactor.execute(inputData);

        verify(mockUserRepository).existsByName("Paul");
    }
}