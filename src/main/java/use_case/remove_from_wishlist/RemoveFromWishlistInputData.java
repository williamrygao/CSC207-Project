package use_case.remove_from_wishlist;

import entity.Listing;

/**
 * The Input Data for the Remove from wishlist Use Case.
 */
public class RemoveFromWishlistInputData {
    /**
     * String username.
     */
    private final String username;

    private final String password;

    private final Listing listing;

    /**
     * RemoveFromWishlistInputData method.
     *
     * @param username the username
     * @param password the password
     * @param listing the listing
     */
    public RemoveFromWishlistInputData(final String username, final String password, final Listing listing) {
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}
