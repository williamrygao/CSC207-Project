package use_case.wishlist.add_to_wishlist;

import entity.listing.Listing;
import entity.user.User;

/**
 * Data Access Interface for the Add To Wishlist Use Case.
 */
public interface AddToWishlistUserDataAccessInterface {
    /**
     * Adds listing to user's wishlist.
     * @param user user adding listing to their wishlist
     * @param listing listing to be added to user's wishlist
     */
    void addToWishlist(User user, Listing listing);

    /**
     * Getter for the User associated with this username.
     * @param username username of the User to be returned
     * @return User associated with this username
     */
    User get(String username);

    /**
     * Exists by username.
     * @param username the username to search for
     * @return true if a user with this username exists
     */
    boolean existsByName(String username);
}
