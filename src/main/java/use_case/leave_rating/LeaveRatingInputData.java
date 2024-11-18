package use_case.leave_rating;

/**
 * The Input Data for the Leave Rating Use Case.
 */
public class LeaveRatingInputData {
    private final String bookID;
    private final int rating;

    public LeaveRatingInputData(String bookID, int rating) {
        this.bookID = bookID;
        this.rating = rating;
    }

    String getBookID() {
        return bookID;
    }

    int getRating() {
        return rating;
    }
}
