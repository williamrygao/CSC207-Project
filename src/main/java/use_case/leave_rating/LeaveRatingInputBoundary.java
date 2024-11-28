package use_case.leave_rating;

/**
 * Input Boundary for actions which are related to leaving a rating.
 */
public interface LeaveRatingInputBoundary {

    /**
     * Executes the Leave rating use case.
     * @param leaveRatingInputData the input data
     */
    void execute(LeaveRatingInputData leaveRatingInputData);
}

