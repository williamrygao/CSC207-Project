package interface_adapter.leave_rating;

public class LeaveRatingController {
    private final LeaveRatingPresenter presenter;

    public LeaveRatingController(LeaveRatingPresenter presenter) {
        this.presenter = presenter;
    }

    public void execute(String bookID, double rating) {
        // Validate inputs
        if (bookID == null || bookID.trim().isEmpty()) {
            presenter.showInvalidRatingMessage("Book ID cannot be empty.");
            return;
        }

        if (rating < 1 || rating > 10) {
            presenter.showInvalidRatingMessage("Rating must be between 1 and 10.");
            return;
        }

        // Interact with the Firebase database (placeholder logic)
        boolean success = updateBookRatingInDatabase(bookID, rating);

        if (success) {
            presenter.showSuccessMessage("Successfully rated the book with ID: " + bookID + ".");
        } else {
            presenter.showErrorMessage("Failed to update the rating for the book.");
        }
    }

    // Placeholder for Firebase interaction
    private boolean updateBookRatingInDatabase(String bookID, double rating) {
        // Actual implementation will interact with Firebase to store the rating
        // Replace this with actual database code
        System.out.println("Updated bookID " + bookID + " with rating " + rating);
        return true; // Simulate success
    }
}