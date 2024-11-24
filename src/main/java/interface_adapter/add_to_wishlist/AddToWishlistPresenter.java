package interface_adapter.add_to_wishlist;

import interface_adapter.remove_from_wishlist.WishlistViewModel;
import use_case.add_to_wishlist.AddToWishlistOutputBoundary;
import use_case.add_to_wishlist.AddToWishlistOutputData;

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
        wishlistViewModel.firePropertyChanged("wishlist");
    }

    @Override
    public void prepareFailView(String error) {

    }
}
