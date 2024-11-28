package interface_adapter.add_to_wishlist;

import entity.Listing;
import use_case.add_to_wishlist.AddToWishlistInputBoundary;
import use_case.add_to_wishlist.AddToWishlistInputData;

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
     * @param password password of wishlist user
     * @param listing listing to be added to wishlist
     */
    public void execute(String username, String password, Listing listing) {
        final AddToWishlistInputData addToWishlistInputData = new AddToWishlistInputData(username, password, listing);
        addToWishlistInteractor.execute(addToWishlistInputData);
    }
}
