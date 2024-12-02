package interface_adapter.leave_rating;

/**
 * The state representing the leave rating process.
 */
public class LeaveRatingState {
    private String bookID = "";           // Book identifier
    private double rating = 0;            // Rating value
    private String error = "";            // Error message
    private String successMessage = "";   // Success message
    private String username = "";         // Username, assuming this is part of the state

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

    public void setNewRating(double rating) {
        this.rating = rating;
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
