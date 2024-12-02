package use_case.wishlist.view_wishlist;

import java.util.List;

import entity.listing.Listing;

/**
 * Output Data for the View Wishlist Use Case.
 */
public class ViewWishlistOutputData {

    private final String username;
    private final boolean useCaseFailed;
    private final List<Listing> wishlist;

    public ViewWishlistOutputData(String username, List<Listing> wishlist, boolean useCaseFailed) {
        this.username = username;
        this.wishlist = wishlist;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public List<Listing> getWishlist() {
        return wishlist;
    }
}
