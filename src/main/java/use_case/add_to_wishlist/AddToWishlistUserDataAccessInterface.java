package use_case.add_to_wishlist;

import entity.Listing;
import entity.User;

public interface AddToWishlistUserDataAccessInterface {
    String getCurrentUsername();

    void setCurrentUsername(String username);

    void addToWishlist(User user, Listing listing);
}
