package use_case.leave_rating;

/**
 * Output Boundary for the Leave Rating Use Case.
 */
public interface LeaveRatingOutputBoundary {
    /**
     * Boundary.
     * @param LeaveRatingOutputData boundary
     */
    void prepareSuccessView(LeaveRatingOutputData LeaveRatingOutputData);
    /**
     * Boundary.
     * @param errorMessage error
     */

    void prepareFailView(String errorMessage);
}
