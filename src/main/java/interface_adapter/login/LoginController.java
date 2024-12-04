package interface_adapter.login;

import java.awt.*;

import javax.swing.*;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * The controller for the Login Use Case.
 */
public class LoginController {

    private final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String username, String password) {
        final LoginInputData loginInputData = new LoginInputData(username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }

    /**
     * Executes the Login Use Case for errors.
     * @param errorMessage the error message to display to user
     * @param center where the panel will be centered
     */
    public void error(Component center, String errorMessage) {
        JOptionPane.showMessageDialog(center, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
