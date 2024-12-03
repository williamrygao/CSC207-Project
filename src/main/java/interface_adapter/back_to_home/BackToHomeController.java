package interface_adapter.back_to_home;

import use_case.back_to_home.BackToHomeInputBoundary;

/**
 * The controller for the Back To Home Use Case.
 */
public class BackToHomeController {

    private BackToHomeInputBoundary backToHomeInteractor;

    /**
     * Back To Home Controller method.
     * @param backToHomeInteractor the use case interactor
     */
    public BackToHomeController(final BackToHomeInputBoundary backToHomeInteractor) {
        this.backToHomeInteractor = backToHomeInteractor;
    }

    /**
     * Executes the Back To Home Use Case.
     */
    public void execute() {
        backToHomeInteractor.execute();
    }
}
