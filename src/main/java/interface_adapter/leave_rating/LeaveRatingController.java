package interface_adapter.leave_rating;

import use_case.leave_rating.LeaveRatingInputBoundary;
import use_case.leave_rating.LeaveRatingInputData;

/**
 * The Controller for the Leave Rating Use Case.
 */
public class LeaveRatingController {
    private final LeaveRatingInputBoundary leaveRatingInteractor;

    /**
     * Constructs a LeaveRatingController.
     *
     * @param leaveRatingInteractor The input boundary for the Leave Rating use case.
     */
    public LeaveRatingController(LeaveRatingInputBoundary leaveRatingInteractor) {
        this.leaveRatingInteractor = leaveRatingInteractor;
    }

    /**
     * Executes the Leave Rating Use Case.
     *
     * @param username  The username of the user leaving the rating.
     * @param password  The password of the user.
     * @param bookID    The ID of the book being rated.
     * @param newRating The new rating (1-10).
     */
    public void execute(String username, String password, String bookID, int newRating) {
        // Validate inputs
        if (bookID == null || bookID.trim().isEmpty()) {

            return;
        }

        if (newRating < 1 || newRating > 10) {

            return;
        }

        // Create the input data object
        final LeaveRatingInputData inputData = new LeaveRatingInputData(username, password, bookID, newRating);

        // Pass the input data to the interactor
        leaveRatingInteractor.execute(inputData);
    }
}
