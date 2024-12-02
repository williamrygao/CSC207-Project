package use_case.leave_rating;

import entity.listing.Listing;
import entity.Rating;
import entity.user.User;

/**
 * DAO for the Leave Rating Use Case.
 */
public interface LeaveRatingDataAccessInterface {

    /**
     * Checks if a given book is associated with a Rating object already.
     * @param bookID the ID of the book to check for
     * @return true if the book already has a Rating object, false if it doesn't.
     */
    boolean existsByBookID(String bookID);

    /**
     * Saves a new rating to a book's Rating object.
     * @param rating the new rating to be saved to a book
     */
    void save(Rating rating);

    /**
     * Retrieve the list of all ratings for a certain book.
     * @param bookID the ID of the book
     * @return the Rating object containing the ratings for the specified book.
     */
    Rating getRatingsByBookID(String bookID);

    /**
     * Return current username.
     * @return the username of the currently logged-in user as a String.
     */
    String getCurrentUsername();

    /**
     * Set new username.
     * @param username the new username
     */
    void setCurrentUsername(String username);

    /**
     * A user leaves a new rating for a book.
     * @param user the user leaving the rating
     * @param rating the rating object associated with the book
     * @param newRating the rating that the user is leaving behind
     * @param listing the listing that the new rating is being left for
     */
    void leaveRating(User user, Rating rating, Integer newRating, Listing listing);
}
