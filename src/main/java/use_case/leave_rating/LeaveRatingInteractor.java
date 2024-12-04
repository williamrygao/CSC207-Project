package use_case.leave_rating;

import entity.Rating;
/**
 * The Leave Rating Interactor.
 */
public class LeaveRatingInteractor implements LeaveRatingInputBoundary {
    private final LeaveRatingDataAccessInterface ratingDataAccess;
    private final LeaveRatingOutputBoundary leaveRatingPresenter;

    /**
     * Constructs a LeaveRatingInteractor.
     *
     * @param ratingDataAccess      The data access interface for ratings.
     * @param leaveRatingPresenter  The output boundary for preparing responses.
     */
    public LeaveRatingInteractor(LeaveRatingDataAccessInterface ratingDataAccess,
                                 LeaveRatingOutputBoundary leaveRatingPresenter) {
        this.ratingDataAccess = ratingDataAccess;
        this.leaveRatingPresenter = leaveRatingPresenter;
    }

    /**
     * Executes the Leave Rating Use Case.
     *
     * @param leaveRatingInputData The input data for leaving a rating.
     */
    @Override
    public void execute(LeaveRatingInputData leaveRatingInputData) {
        String username = leaveRatingInputData.getUsername();
        String bookId = leaveRatingInputData.getBookid();
        Integer newRating = leaveRatingInputData.getNewRating();

        // Validate that the book exists
        if (!ratingDataAccess.existsByBookID(bookId)) {
            leaveRatingPresenter.prepareFailView("Book not found for ID: " + bookId);
        }

        // Retrieve the current ratings or create a new one
        Rating rating = ratingDataAccess.getRatingsByBookID(bookId);
        if (rating == null) {
            rating = new Rating(bookId);
        }

        // Add the new rating
        try {
            rating.addRating(newRating);
            ratingDataAccess.save(rating);

            // Prepare the success output
            LeaveRatingOutputData outputData = new LeaveRatingOutputData(username, false);
            leaveRatingPresenter.prepareSuccessView(outputData);

        } catch (IllegalArgumentException e) {
            // Handle invalid rating (e.g., out of range)
            leaveRatingPresenter.prepareFailView(e.getMessage());
        } catch (Exception e) {
            // General failure
            leaveRatingPresenter.prepareFailView("Failed to leave rating due to unexpected error.");
        }
    }
}
