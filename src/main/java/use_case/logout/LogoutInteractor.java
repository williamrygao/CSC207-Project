package use_case.logout;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    /**
     * The userDataAccessObject.
     */
    private LogoutUserDataAccessInterface userDataAccessObject;
    /**
     * The logoutPresenter.
     */
    private LogoutOutputBoundary logoutPresenter;

    /**
     * LogoutInteractor method.
     * @param userDataAccessInterface the userDataAccessInterface
     * @param logoutOutputBoundary the logoutOutputBoundary
     */
    public LogoutInteractor(final LogoutUserDataAccessInterface userDataAccessInterface,
                            final LogoutOutputBoundary logoutOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.logoutPresenter = logoutOutputBoundary;
    }

    /**
     * Override execute method.
     * @param logoutInputData the input data
     */
    @Override
    public void execute(final LogoutInputData logoutInputData) {
        final String username = logoutInputData.getUsername();

        // If the username is null, you can log a message or handle the error gracefully instead of triggering a failure
        if (username == null || username.isEmpty()) {
            logoutPresenter.prepareFailView("Username not found.");
        }
        else {
            // Proceed with logging out
            userDataAccessObject.setCurrentUsername(null);

            final LogoutOutputData logoutOutputData = new LogoutOutputData(username, false);
            logoutPresenter.prepareSuccessView(logoutOutputData);
        }
    }
}

