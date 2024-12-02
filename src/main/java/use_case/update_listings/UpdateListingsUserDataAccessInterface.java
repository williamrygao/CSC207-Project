package use_case.update_listings;

import java.util.List;

import entity.listing.Listing;
import entity.user.User;

/**
 * DAO for the Update Listings Use Case.
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
     * Get this user's wishlist.
     * @param user the user
     * @return wishlist
     */
    List<Listing> getWishlist(User user);
}
