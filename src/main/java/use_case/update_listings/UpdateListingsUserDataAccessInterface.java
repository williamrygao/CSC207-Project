package use_case.update_listings;

import java.util.List;

import entity.listing.Listing;
import entity.user.User;

/**
 * Represents the Data Access Object (DAO) for user-related operations
 * in the Update Listings use case. This interface abstracts data retrieval
 * and manipulation, enabling flexibility in storage implementation.
 */
public interface UpdateListingsUserDataAccessInterface {

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

    /**
     * Sets the username indicating who is the current user of the application.
     * @param username the new current username; null to indicate that no one is currently logged into the application.
     */
    void setCurrentUsername(String username);

    /**
     * Retrieves the wishlist of the specified user.
     *
     * @param user the User object whose wishlist is to be retrieved.
     * @return a list of listings representing the user's wishlist.
     */
    List<Listing> getWishlist(User user);
}
