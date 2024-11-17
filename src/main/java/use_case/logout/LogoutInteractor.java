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
    public LogoutInteractor(final LogoutUserDataAccessInterface
                                    userDataAccessInterface,
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
        // * get the username out of the input data,
        // * set the username to null in the DAO
        // * instantiate the `LogoutOutputData`,which needs to contain username.
        // * tell the presenter to prepare a success view.
        final String username = logoutInputData.getUsername();

        // I (Victor) added this fail condition but unsure if needed
        if (username == null) {
            logoutPresenter.prepareFailView("Username not found.");
        }
        else {
            userDataAccessObject.setCurrentUsername(null);

            // I'm pretty sure the useCaseFailed argument is supposed to be
            // false unless I interpreted it wrong
            // Documenting here just in case (delete if all tests pass)
            final LogoutOutputData logoutOutputData = new LogoutOutputData(
                    username, false);
            logoutPresenter.prepareSuccessView(logoutOutputData);
        }
    }
}

