package use_case.leave_rating;

/**
 * Input Boundary for actions which are related to leaving a rating.
 */
public interface LeaveRatingInputBoundary {

    /**
     * Executes the Remove from wishlist use case.
     * @param LeaveRatingInputData the input data
     */
    void execute(LeaveRatingInputData LeaveRatingInputData);
}

