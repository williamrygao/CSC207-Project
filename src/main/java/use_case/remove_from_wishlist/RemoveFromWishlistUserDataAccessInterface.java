package use_case.remove_from_wishlist;

import entity.Listing;

public interface RemoveFromWishlistUserDataAccessInterface {
    String getCurrentUsername();

    void setCurrentUsername(String username);

    void removeListing(Listing listing);
}
