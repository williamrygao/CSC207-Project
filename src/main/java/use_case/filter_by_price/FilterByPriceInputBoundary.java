package use_case.filter_by_rating;

/**
 * Input Boundary for actions which are related to filtering books by rating.
 */
public interface FilterByRatingInputBoundary {
    /**
     * Executes the Filter By Rating use case.
     * @param filterByRatingInputData the input data
     */
    void execute(FilterByRatingInputData filterByRatingInputData);
}
