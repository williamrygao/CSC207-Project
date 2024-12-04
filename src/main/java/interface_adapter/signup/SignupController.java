package interface_adapter.signup;

import java.awt.*;

import javax.swing.*;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Signup Use Case.
 */
public class SignupController {

    /**
     * SignupInputBoundary.
     */
    private final SignupInputBoundary userSignupUseCaseInteractor;

    /**
     * Constructs a SignupController with specified signup use case interactor.
     * @param userSignupUseCaseInteractor the interactor responsible for
     *                                    handling user signup operations
     */
    public SignupController(final SignupInputBoundary
                                    userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username to sign up
     * @param password1 the password
     * @param password2 the password repeated
     */
    public void execute(final String username, final String password1, final String password2) {
        final SignupInputData signupInputData = new SignupInputData(username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }

    /**
     * Executes the Signup Use Case for errors.
     * @param errorMessage the error message to display to user
     * @param center where the panel will be centered
     */
    public void error(Component center, String errorMessage) {
        JOptionPane.showMessageDialog(center, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
