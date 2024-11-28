package interface_adapter.remove_from_wishlist;

import entity.Listing;
import use_case.remove_from_wishlist.RemoveFromWishlistInputBoundary;
import use_case.remove_from_wishlist.RemoveFromWishlistInputData;

/**
 * Controller for the Remove From Wishlist Use Case.
 */
public class RemoveFromWishlistController {
    private final RemoveFromWishlistInputBoundary removeFromWishlistInteractor;

    public RemoveFromWishlistController(RemoveFromWishlistInputBoundary removeFromWishlistInteractor) {
        this.removeFromWishlistInteractor = removeFromWishlistInteractor;
    }

    public void execute(String username, String password, Listing listing) {
        final RemoveFromWishlistInputData removeFromWishlistInputData = new RemoveFromWishlistInputData(username, password, listing);
        removeFromWishlistInteractor.execute(removeFromWishlistInputData);
    }
}
