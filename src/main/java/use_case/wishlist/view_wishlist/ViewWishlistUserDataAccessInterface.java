package use_case.wishlist.view_wishlist;

import java.util.List;

import entity.Listing;
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
     * @return the user's wishlist
     */
    List<Listing> getWishlist(User user);
}
