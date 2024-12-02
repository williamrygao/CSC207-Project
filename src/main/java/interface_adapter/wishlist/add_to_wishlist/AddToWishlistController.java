package interface_adapter.wishlist.add_to_wishlist;

import entity.listing.Listing;
import use_case.wishlist.add_to_wishlist.AddToWishlistInputBoundary;
import use_case.wishlist.add_to_wishlist.AddToWishlistInputData;

/**
 * Controller for the Add To Wishlist Use Case.
 */
public class AddToWishlistController {
    private final AddToWishlistInputBoundary addToWishlistInteractor;

    public AddToWishlistController(AddToWishlistInputBoundary addToWishlistInteractor) {
        this.addToWishlistInteractor = addToWishlistInteractor;
    }

    /**
     * Execute the Add To Wishlist Use Case.
     * @param username username of wishlist user
     * @param listing listing to be added to wishlist
     */
    public void execute(String username, Listing listing) {
        final AddToWishlistInputData addToWishlistInputData = new AddToWishlistInputData(username, listing);
        addToWishlistInteractor.execute(addToWishlistInputData);
    }
}
