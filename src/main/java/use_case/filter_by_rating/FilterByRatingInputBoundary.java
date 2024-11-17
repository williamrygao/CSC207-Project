package use_case.filter_by_rating;

/**
 * Input Boundary for actions which are related to filtering books by rating.
 */
public interface FilterByRatingInputBoundary {
    void execute(FilterByRatingInputData filterByRatingInputData);
}
