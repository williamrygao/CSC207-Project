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
     * Retrieves the user with the specified username.
     *
     * @param username the username of the user to retrieve.
     * @return the User object associated with the given username.
     */

    User get(String username);

    /**
     * Sets the current username, indicating the currently logged-in user.
     *
     * @param username the username of the current user; null if no user is logged in.
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
