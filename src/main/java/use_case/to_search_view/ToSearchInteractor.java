package use_case.to_search_view;

/**
 * The To Search Interactor.
 */
public class ToSearchInteractor implements ToSearchInputBoundary {
    private ToSearchOutputBoundary toSearchPresenter;

    public ToSearchInteractor(final ToSearchOutputBoundary toSearchOutputBoundary) {
        this.toSearchPresenter = toSearchOutputBoundary;
    }

    /**
     * Override execute method.
     */
    @Override
    public void execute() {
        toSearchPresenter.prepareSuccessView();
    }
}
