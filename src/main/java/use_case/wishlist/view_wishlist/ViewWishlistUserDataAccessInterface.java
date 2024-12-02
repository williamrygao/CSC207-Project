package use_case.wishlist.view_wishlist;

import java.util.List;

import entity.listing.Listing;
import entity.user.User;

/**
 * DAO for the View Wishlist Use Case.
 */
public interface ViewWishlistUserDataAccessInterface {
    /**
     * Get user with this username.
     * @param username the username
     * @return the associated user
     */
    User get(String username);

    /**
     * Return this User's wishlist.
     * @param user the user
     * @return the user's wishlist
     */
    List<Listing> getWishlist(User user);

    /**
     * Exists by username.
     * @param username the username to search for
     * @return true if a user with this username exists
     */
    boolean existsByName(String username);
}
