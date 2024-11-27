package use_case.leave_rating;

import entity.Listing;

/**
 * The Input Data for the LeaveRating use case.
 */
public class LeaveRatingInputData {

    private final String username;

    private final String password;

    private final Listing listing;

    private final String bookid;

    private final Integer newRating;

     /**
     * LeaveRatingInputData method.
     *
     * @param username the username
     * @param password the password
     * @param listing  the listing
     * @param newRating  the new rating
     */
     public LeaveRatingInputData(final String username, final String password, Listing listing, final Integer newRating) {
        this.username = username;
        this.password = password;
        this.bookid = password;
        this.listing = listing;
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

    public Listing getListing() {
        return listing;
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

