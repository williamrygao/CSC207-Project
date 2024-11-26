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
    private final String repeatPassword;
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
     * @param repeatPassword the re
     */
    public LeaveRatingInputData(final String username, final String password, final String repeatPassword, Listing listing) {
        this.username = username;
        this.password = password;
        this.repeatPassword =
        this.bookid = password;
        this.listing = listing;
    }

}


