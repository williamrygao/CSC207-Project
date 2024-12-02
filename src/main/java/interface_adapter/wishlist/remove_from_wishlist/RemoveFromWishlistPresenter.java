package interface_adapter.wishlist.remove_from_wishlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.wishlist.WishlistViewModel;
import use_case.wishlist.remove_from_wishlist.RemoveFromWishlistOutputBoundary;
import use_case.wishlist.remove_from_wishlist.RemoveFromWishlistOutputData;

/**
 * The Presenter for the Remove From Wishlist Use Case.
 */
public class RemoveFromWishlistPresenter implements RemoveFromWishlistOutputBoundary {
    private final WishlistViewModel wishlistViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;

    public RemoveFromWishlistPresenter(WishlistViewModel wishlistViewModel, HomeViewModel homeViewModel, ViewManagerModel viewManagerModel) {
        this.wishlistViewModel = wishlistViewModel;
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(RemoveFromWishlistOutputData removeFromWishlistOutputData) {
        if (viewManagerModel.getState().equals("home")) {
            homeViewModel.firePropertyChanged("removedFromWishlist");
        }
        else if (viewManagerModel.getState().equals("wishlist")) {
            wishlistViewModel.firePropertyChanged("removedFromWishlist");
        }
    }

    @Override
    public void prepareFailView(String error) {
        if (viewManagerModel.getState().equals("home")) {
            homeViewModel.firePropertyChanged("wishlistRemoveFail");
        }
        else if (viewManagerModel.getState().equals("wishlist")) {
            wishlistViewModel.firePropertyChanged("wishlistRemoveFail");
        }
    }
}
