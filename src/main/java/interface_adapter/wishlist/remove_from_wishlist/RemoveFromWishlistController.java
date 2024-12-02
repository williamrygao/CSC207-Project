package interface_adapter.wishlist.remove_from_wishlist;

import entity.listing.Listing;
import use_case.wishlist.remove_from_wishlist.RemoveFromWishlistInputBoundary;
import use_case.wishlist.remove_from_wishlist.RemoveFromWishlistInputData;

/**
 * Controller for the Remove From Wishlist Use Case.
 */
public class RemoveFromWishlistController {
    private final RemoveFromWishlistInputBoundary removeFromWishlistInteractor;

    public RemoveFromWishlistController(RemoveFromWishlistInputBoundary removeFromWishlistInteractor) {
        this.removeFromWishlistInteractor = removeFromWishlistInteractor;
    }

    /**
     * Execute the Remove From Wishlist Use Case.
     * @param username username of wishlist user
     * @param listing listing to be removed from user's wishlist
     */
    public void execute(String username, Listing listing) {
        final RemoveFromWishlistInputData removeFromWishlistInputData = new RemoveFromWishlistInputData(
                username, listing
        );
        removeFromWishlistInteractor.execute(removeFromWishlistInputData);
    }
}
