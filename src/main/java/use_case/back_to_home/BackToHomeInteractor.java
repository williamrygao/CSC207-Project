package use_case.back_to_home;

/**
 * The Back To Home Interactor.
 */
public class BackToHomeInteractor implements BackToHomeInputBoundary {
    private BackToHomeOutputBoundary backToHomePresenter;

    public BackToHomeInteractor(final BackToHomeOutputBoundary backToHomePresenter) {
        this.backToHomePresenter = backToHomePresenter;
    }

    /**
     * Override execute method.
     */
    @Override
    public void execute() {
        backToHomePresenter.prepareSuccessView();
    }
}
