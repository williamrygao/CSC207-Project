package use_case.to_filter_by_genre;

/**
 * To filter by genre interactor.
 */
public class ToFilterByGenreInteractor implements ToFilterByGenreInputBoundary {
    private final ToFilterByGenreOutputBoundary toFilterByGenrePresenter;

    public ToFilterByGenreInteractor(final ToFilterByGenreOutputBoundary toFilterByGenreOutputBoundary) {
        this.toFilterByGenrePresenter = toFilterByGenreOutputBoundary;
    }

    /**
     * Override execute method.
     */
    @Override
    public void execute() {
        toFilterByGenrePresenter.prepareSuccessView();
    }
}
