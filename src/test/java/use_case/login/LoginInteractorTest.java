package use_case.login;

import data_access.FirebaseUserDataAccessObject;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginInteractorTest {
    public LoginUserDataAccessInterface mockUserRepository;
    public LoginOutputBoundary mockSuccessPresenter;
    public LoginOutputBoundary mockFailurePresenter;

    @BeforeEach
    void setup() {
        // Mocking FirebaseUserDataAccessObject for unit tests
        mockUserRepository = mock(FirebaseUserDataAccessObject.class);
        mockSuccessPresenter = mock(LoginOutputBoundary.class);
        mockFailurePresenter = mock(LoginOutputBoundary.class);
    }

    @Test
    void successTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");

        // For the success test, we need to add Paul to the data access repository before we log in.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        mockUserRepository.save(user);

        // Simulate that the user exists in the repository
        when(mockUserRepository.existsByName("Paul")).thenReturn(true);
        when(mockUserRepository.get("Paul")).thenReturn(user);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(mockUserRepository, successPresenter);
        interactor.execute(inputData);

        verify(mockUserRepository).setCurrentUsername("Paul");
    }

    @Test
    void successUserLoggedInTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");

        // For the success test, we need to add Paul to the data access repository before we log in.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        mockUserRepository.save(user);

        // Simulate that the user exists in the repository
        when(mockUserRepository.existsByName("Paul")).thenReturn(true);
        when(mockUserRepository.get("Paul")).thenReturn(user);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(mockUserRepository, successPresenter);
        interactor.execute(inputData);

        verify(mockUserRepository).setCurrentUsername("Paul");
    }

    @Test
    void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData("Paul", "wrongPassword");

        // For this failure test, we need to add Paul to the data access repository before we log in, and
        // the passwords should not match.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        mockUserRepository.save(user);

        when(mockUserRepository.existsByName("Paul")).thenReturn(true);
        when(mockUserRepository.get("Paul")).thenReturn(user);

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Your password is incorrect.", error);
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(mockUserRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserDoesNotExistTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");

        // Add Paul to the repo so that when we check later they already exist

        when(mockUserRepository.existsByName("Paul")).thenReturn(false);

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("We cannot find an account with that username.", error);
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(mockUserRepository, failurePresenter);
        interactor.execute(inputData);
    }
}