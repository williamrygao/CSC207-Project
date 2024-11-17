package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

/**
 * The controller for the Logout Use Case.
 */
public class LogoutController {

    private LogoutInputBoundary logoutUseCaseInteractor;

    /**
     * LogoutController method.
     * @param logoutUseCaseInteractor the use case interactor
     */
    public LogoutController(final LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }

    /**
     * Executes the Logout Use Case.
     * @param username the username of the user logging in
     */
    public void execute(final String username) {
        // 1. instantiate the `LogoutInputData`, which should contain username.
        final LogoutInputData logoutInputData = new LogoutInputData(username);
        // 2. tell the Interactor to execute.
        logoutUseCaseInteractor.execute(logoutInputData);
    }
}
