package use_case.wishlist.remove_from_wishlist;

import entity.Listing;
import entity.user.User;

public interface RemoveFromWishlistUserDataAccessInterface {
    String getCurrentUsername();

    void setCurrentUsername(String username);

    void removeFromWishlist(User user, Listing listing);

    User get(String username);

    /**
     * Exists by username.
     * @param username the username to search for
     * @return true if a user with this username exists
     */
    boolean existsByName(String username);
}
