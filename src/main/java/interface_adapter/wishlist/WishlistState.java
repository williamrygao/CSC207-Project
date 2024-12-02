package interface_adapter.wishlist;

import java.util.ArrayList;
import java.util.List;

import entity.listing.Listing;

/**
 * The State information representing the wishlist user.
 */
public class WishlistState {
    private String username = "";

    private List<Listing> wishlist = new ArrayList<>();

    public WishlistState(WishlistState copy) {
        username = copy.username;
        wishlist = copy.wishlist;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public WishlistState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Listing> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Listing> wishlist) {
        this.wishlist = wishlist;
    }
}
