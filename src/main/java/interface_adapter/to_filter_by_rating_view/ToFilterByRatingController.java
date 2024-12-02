package interface_adapter.to_filter_by_rating_view;

import use_case.to_filter_by_rating_view.ToFilterByRatingInputBoundary;

/**
 * The controller for the To Filter By Rating Use Case.
 */
public class ToFilterByRatingController {

    private final ToFilterByRatingInputBoundary toFilterByRatingInteractor;

    /**
     * ToFilterByRatingController method.
     * @param toFilterByRatingInteractor the use case interactor
     */
    public ToFilterByRatingController(final ToFilterByRatingInputBoundary toFilterByRatingInteractor) {
        this.toFilterByRatingInteractor = toFilterByRatingInteractor;
    }

    /**
     * Executes the To Filter By Rating Use Case.
     */
    public void execute() {
        toFilterByRatingInteractor.execute();
    }
}
