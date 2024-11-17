package interface_adapter.back_to_home;

import use_case.back_to_home.BackToHomeInputBoundary;

/**
 * The controller for the Back To Logged In Use Case.
 */
public class BackToHomeController {

    private BackToHomeInputBoundary backToLoggedInUseCaseInteractor;

    /**
     * Back To Logged In Controller method.
     * @param backToLoggedInUseCaseInteractor the use case interactor
     */
    public BackToHomeController(final BackToHomeInputBoundary backToLoggedInUseCaseInteractor) {
        this.backToLoggedInUseCaseInteractor = backToLoggedInUseCaseInteractor;
    }

    /**
     * Executes the Back To Home Use Case.
     */
    public void execute() {
        backToLoggedInUseCaseInteractor.execute();
    }
}
