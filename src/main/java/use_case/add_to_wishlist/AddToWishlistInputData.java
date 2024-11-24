package use_case.add_to_wishlist;

import entity.Listing;

/**
 * The Input Data for the Add to wishlist Use Case.
 */
public class AddToWishlistInputData {
    /**
     * String username.
     */
    private final String username;

    private final String password;

    private final Listing listing;

    /**
     * AddToWishlistInputData method.
     *
     * @param username the username
     * @param password the password
     * @param listing the listing
     */
    public AddToWishlistInputData(final String username, final String password, final Listing listing) {
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
