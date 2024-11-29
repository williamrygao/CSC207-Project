package interface_adapter.wishlist.add_to_wishlist;

import interface_adapter.wishlist.remove_from_wishlist.WishlistViewModel;
import use_case.wishlist.add_to_wishlist.AddToWishlistOutputBoundary;
import use_case.wishlist.add_to_wishlist.AddToWishlistOutputData;

/**
 * The Presenter for the Add To Wishlist Use Case.
 */
public class AddToWishlistPresenter implements AddToWishlistOutputBoundary {
    private final WishlistViewModel wishlistViewModel;

    public AddToWishlistPresenter(WishlistViewModel wishlistViewModel) {
        this.wishlistViewModel = wishlistViewModel;
    }

    @Override
    public void prepareSuccessView(AddToWishlistOutputData addToWishlistOutputData) {
    }

    @Override
    public void prepareFailView(String error) {

    }
}
