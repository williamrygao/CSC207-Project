package use_case.wishlist.add_to_wishlist;

import entity.Listing;
import entity.user.User;

public interface AddToWishlistUserDataAccessInterface {
    void addToWishlist(User user, Listing listing);

    User get(String username);
}
