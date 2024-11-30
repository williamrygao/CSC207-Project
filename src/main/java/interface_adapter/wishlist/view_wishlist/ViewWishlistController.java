package interface_adapter.wishlist.view_wishlist;

import use_case.wishlist.view_wishlist.ViewWishlistInputBoundary;
import use_case.wishlist.view_wishlist.ViewWishlistInputData;

/**
 * The controller for the View Wishlist Use Case.
 */
public class ViewWishlistController {

    private final ViewWishlistInputBoundary viewWishlistInteractor;

    public ViewWishlistController(final ViewWishlistInputBoundary viewWishlistInteractor) {
        this.viewWishlistInteractor = viewWishlistInteractor;
    }

    /**
     * Executes the View Wishlist Use Case.
     * @param username the username
     */
    public void execute(String username) {
        final ViewWishlistInputData viewWishlistInputData = new ViewWishlistInputData(username);
        viewWishlistInteractor.execute(viewWishlistInputData);
    }
}
