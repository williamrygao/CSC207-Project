package use_case.remove_from_wishlist;

import entity.Listing;
import entity.User;

public interface RemoveFromWishlistUserDataAccessInterface {
    String getCurrentUsername();

    void setCurrentUsername(String username);

    void removeFromWishlist(User user, Listing listing);
}
