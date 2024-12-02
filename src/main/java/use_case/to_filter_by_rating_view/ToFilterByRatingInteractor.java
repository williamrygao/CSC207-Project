package use_case.to_filter_by_rating_view;

/**
 * The To Filter By Rating Interactor.
 */
public class ToFilterByRatingInteractor implements ToFilterByRatingInputBoundary {
    private ToFilterByRatingOutputBoundary toFilterByRatingPresenter;

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