package use_case.to_filter_by_rating;

/**
 * The output boundary for the To Filter By Rating View Use Case.
 */
public interface ToFilterByRatingOutputBoundary {
    /**
     * Prepares the success view for the To Filter By Rating Use Case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the to Filter By Rating Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}