package interface_adapter.add_to_wishlist;

import entity.Listing;
import use_case.add_to_wishlist.AddToWishlistInputBoundary;
import use_case.add_to_wishlist.AddToWishlistInputData;
import view.WishlistView;

/**
 * Controller for the Add To Wishlist Use Case.
 */
public class AddToWishlistController {
    private final AddToWishlistInputBoundary addToWishlistInteractor;
    private final WishlistView wishlistView;

    public AddToWishlistController(AddToWishlistInputBoundary addToWishlistInteractor, WishlistView wishlistView) {
        this.addToWishlistInteractor = addToWishlistInteractor;
        this.wishlistView = wishlistView;
    }

    public void execute(String username, String password, Listing listing) {
        final AddToWishlistInputData addToWishlistInputData = new AddToWishlistInputData(username, password, listing);
        addToWishlistInteractor.execute(addToWishlistInputData);
    }
}
