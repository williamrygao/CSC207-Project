package interface_adapter.remove_from_wishlist;

import use_case.remove_from_wishlist.RemoveFromWishlistOutputBoundary;
import use_case.remove_from_wishlist.RemoveFromWishlistOutputData;

/**
 * The Presenter for the Remove From Wishlist Use Case.
 */
public class RemoveFromWishlistPresenter implements RemoveFromWishlistOutputBoundary {
    private final WishlistViewModel wishlistViewModel;

    public RemoveFromWishlistPresenter(WishlistViewModel wishlistViewModel) {
        this.wishlistViewModel = wishlistViewModel;
    }

    @Override
    public void prepareSuccessView(RemoveFromWishlistOutputData removeFromWishlistOutputData) {
        wishlistViewModel.firePropertyChanged("wishlist");
    }

    @Override
    public void prepareFailView(String error) {

    }
}
