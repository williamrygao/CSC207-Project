package use_case.wishlist.remove_from_wishlist;

import entity.listing.Listing;

/**
 * The Input Data for the Remove from wishlist Use Case.
 */
public class RemoveFromWishlistInputData {
    /**
     * String username.
     */
    private final String username;

    private final Listing listing;

    /**
     * RemoveFromWishlistInputData method.
     *
     * @param username the username
     * @param listing the listing
     */
    public RemoveFromWishlistInputData(final String username, final Listing listing) {
        this.username = username;
        this.listing = listing;
    }

    /**
     * * Getter for username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    public Listing getListing() {
        return listing;
    }
}
