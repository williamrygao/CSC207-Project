package use_case.leave_rating;

import entity.listing.Listing;

/**
 * The Input Data for the LeaveRating use case.
 */
public class LeaveRatingInputData {

    private final String username;

    private final String password;


    private final String bookid;

    private final Integer newRating;

     /**
     * LeaveRatingInputData method.
     *
     * @param username the username
     * @param password the password
     * @param newRating  the new rating
     */
     public LeaveRatingInputData(final String username, final String password, String bookid, final Integer newRating) {
        this.username = username;
        this.password = password;
        this.bookid = bookid;
        this.newRating = newRating;
    }


    /**
     * * Getter for username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    public String getBookid() {
        return bookid;
    }

    public String getPassword() {
        return password;
    }

    public Integer getNewRating() {
        return newRating;
    }
}

