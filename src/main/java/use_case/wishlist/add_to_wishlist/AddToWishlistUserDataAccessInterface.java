package use_case.wishlist.add_to_wishlist;

import entity.Listing;
import entity.user.User;

public interface AddToWishlistUserDataAccessInterface {
    String getCurrentUsername();

    void setCurrentUsername(String username);

    void addToWishlist(User user, Listing listing);

    User get(String username);
}
