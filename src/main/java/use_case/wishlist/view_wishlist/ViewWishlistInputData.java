package use_case.wishlist.view_wishlist;

/**
 * The Input Data for the View Wishlist Use Case.
 */
public class ViewWishlistInputData {
    private final String username;

    public ViewWishlistInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
