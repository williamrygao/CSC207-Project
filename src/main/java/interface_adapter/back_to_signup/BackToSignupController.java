package interface_adapter.back_to_signup;

import use_case.back_to_signup.BackToSignupInputBoundary;

/**
 * The controller for the Back To Signup Use Case.
 */
public class BackToSignupController {

    private BackToSignupInputBoundary backToSignupInteractor;

    /**
     * Back To Signup Controller method.
     * @param backToSignupInteractor the use case interactor
     */
    public BackToSignupController(final BackToSignupInputBoundary backToSignupInteractor) {
        this.backToSignupInteractor = backToSignupInteractor;
    }

    /**
     * Executes the Back To Signup Use Case.
     */
    public void execute() {
        backToSignupInteractor.execute();
    }
}
