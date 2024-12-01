package use_case.wishlist.remove_from_wishlist;

import entity.Listing;
import entity.user.User;

public interface RemoveFromWishlistUserDataAccessInterface {
    String getCurrentUsername();

    void setCurrentUsername(String username);

    void removeFromWishlist(User user, Listing listing);

    User get(String username);
}
