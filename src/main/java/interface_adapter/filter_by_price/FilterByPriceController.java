package interface_adapter.filter_by_rating;

import use_case.filter_by_price.FilterByPriceInputBoundary;
import use_case.filter_by_price.FilterByPriceInputData;

/**
 * The controller for the Filter By Rating Use Case.
 */
public class FilterByRatingController {

    private final FilterByPriceInputBoundary filterByRatingInteractor;

    public FilterByRatingController(FilterByPriceInputBoundary filterByRatingInteractor) {
        this.filterByRatingInteractor = filterByRatingInteractor;
    }

    /**
     * Executes the Filter By Rating Use Case.
     * @param rating the rating of books that the user wants to filter by
     */
    public void execute(int rating) {
        final FilterByPriceInputData filterByPriceInputData = new FilterByPriceInputData(rating);
        filterByRatingInteractor.execute(filterByPriceInputData);
    }
}

