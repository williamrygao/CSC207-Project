package interface_adapter.leave_rating;

/**
 * The state representing the leave rating process.
 */
public class LeaveRatingState {
    // Book identifier
    private String bookID = "";
    // Rating value
    private double rating;
    // Error message
    private String error = "";
    // Success message
    private String successMessage = "";
    // Username, assuming this is part of the state
    private String username = "";

    // Getters and Setters
    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public double getRating() {
        return rating;
    }

    public void setNewRating(double bookRating) {
        this.rating = bookRating;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public double getNewRating() {
        return rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
