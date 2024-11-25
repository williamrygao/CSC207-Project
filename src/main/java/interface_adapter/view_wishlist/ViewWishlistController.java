package interface_adapter.view_wishlist;

import use_case.view_wishlist.ViewWishlistInputBoundary;

/**
 * The controller for the View Wishlist Use Case.
 */
public class ViewWishlistController {
    private ViewWishlistInputBoundary viewWishlistInteractor;

    public ViewWishlistController(final ViewWishlistInputBoundary viewWishlistInteractor) {
        this.viewWishlistInteractor = viewWishlistInteractor;
    }

    public void execute() {
        viewWishlistInteractor.execute();
    }
}
