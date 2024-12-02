package use_case.to_filter_by_rating;

/**
 * The To Filter By Rating Interactor.
 */
public class ToFilterByRatingInteractor implements ToFilterByRatingInputBoundary {
    private final ToFilterByRatingOutputBoundary toFilterByRatingPresenter;

    public ToFilterByRatingInteractor(final ToFilterByRatingOutputBoundary toFilterByRatingOutputBoundary) {
        this.toFilterByRatingPresenter = toFilterByRatingOutputBoundary;
    }

    /**
     * Override execute method.
     */
    @Override
    public void execute() {
        toFilterByRatingPresenter.prepareSuccessView();
    }
}
