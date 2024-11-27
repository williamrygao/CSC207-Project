package use_case.leave_rating;

import entity.Listing;
import entity.Rating;
import entity.User;

/**
 * The Input Data for the LeaveRating use case.
 */
public class LeaveRatingInputData {

    private final String username;
    private final String password;
    private final Listing listing;
    private final String bookid;
    private final Rating rating;
    private final Integer newRating;

    /**
     * LeaveRatingInputData method.
     *
     * @param username the username
     * @param password the password
     * @param listing the listing
     */
    public LeaveRatingInputData(final String username, final String password,, Listing listing, final Integer newRating) {
        this.username = username;
        this.password = password;
        this.bookid = password;
        this.listing = listing;
        this.newRating = newRating;

    }

}


