package interface_adapter.filter_by_rating;

import use_case.filter_by_rating.FilterByRatingInputBoundary;
import use_case.filter_by_rating.FilterByRatingInputData;

/**
 * The controller for the Filter By Rating Use Case.
 */
public class FilterByRatingController {

    private final FilterByRatingInputBoundary filterByRatingInteractor;

    public FilterByRatingController(FilterByRatingInputBoundary filterByRatingInteractor) {
        this.filterByRatingInteractor = filterByRatingInteractor;
    }

    /**
     * Executes the Filter By Rating Use Case.
     * @param rating the rating of books that the user wants to filter by
     */
    public void execute(int rating) {
        final FilterByRatingInputData filterByRatingInputData = new FilterByRatingInputData(rating);

        filterByRatingInteractor.execute(filterByRatingInputData);
    }
}
