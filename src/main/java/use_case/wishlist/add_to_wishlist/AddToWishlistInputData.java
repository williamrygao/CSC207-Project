package use_case.wishlist.add_to_wishlist;

import entity.listing.Listing;

/**
 * The Input Data for the Add to wishlist Use Case.
 */
public class AddToWishlistInputData {
    /**
     * String username.
     */
    private final String username;

    private final Listing listing;

    /**
     * AddToWishlistInputData method.
     *
     * @param username the username
     * @param listing  the listing
     */
    public AddToWishlistInputData(final String username, final Listing listing) {
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
