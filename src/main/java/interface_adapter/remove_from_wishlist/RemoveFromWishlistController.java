package interface_adapter.remove_from_wishlist;

import entity.Listing;
import use_case.remove_from_wishlist.RemoveFromWishlistInputBoundary;
import use_case.remove_from_wishlist.RemoveFromWishlistInputData;
import view.WishlistView;

/**
 * Controller for the Remove From Wishlist Use Case.
 */
public class RemoveFromWishlistController {
    private final RemoveFromWishlistInputBoundary userRemoveFromWishlistUseCaseInteractor;
    private final WishlistView wishlistView;

    public RemoveFromWishlistController(RemoveFromWishlistInputBoundary userRemoveFromWishlistUseCaseInteractor, WishlistView wishlistView) {
        this.userRemoveFromWishlistUseCaseInteractor = userRemoveFromWishlistUseCaseInteractor;
        this.wishlistView = wishlistView;
    }

    public void execute(String username, Listing listing) {
        final RemoveFromWishlistInputData removeFromWishlistInputData = new RemoveFromWishlistInputData(username, listing);
        userRemoveFromWishlistUseCaseInteractor.execute(removeFromWishlistInputData);
    }
}
