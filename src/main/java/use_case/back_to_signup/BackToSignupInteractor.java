package use_case.back_to_signup;

/**
 * The Back To Signup Interactor.
 */
public class BackToSignupInteractor implements BackToSignupInputBoundary {
    private BackToSignupOutputBoundary backToSignupPresenter;

    public BackToSignupInteractor(final BackToSignupOutputBoundary backToSignupPresenter) {
        this.backToSignupPresenter = backToSignupPresenter;
    }

    /**
     * Override execute method.
     */
    @Override
    public void execute() {
        backToSignupPresenter.prepareSuccessView();
    }
}
