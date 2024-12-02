package interface_adapter.wishlist.add_to_wishlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.wishlist.WishlistViewModel;
import use_case.wishlist.add_to_wishlist.AddToWishlistOutputBoundary;
import use_case.wishlist.add_to_wishlist.AddToWishlistOutputData;

/**
 * The Presenter for the Add To Wishlist Use Case.
 */
public class AddToWishlistPresenter implements AddToWishlistOutputBoundary {
    private final WishlistViewModel wishlistViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddToWishlistPresenter(WishlistViewModel wishlistViewModel, HomeViewModel homeViewModel, ViewManagerModel viewManagerModel) {
        this.wishlistViewModel = wishlistViewModel;
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddToWishlistOutputData addToWishlistOutputData) {
        if (viewManagerModel.getState().equals("home")) {
            homeViewModel.firePropertyChanged("addedToWishlist");
        }
        else if (viewManagerModel.getState().equals("wishlist")) {
            wishlistViewModel.firePropertyChanged("addedToWishlist");
        }
    }

    @Override
    public void prepareFailView(String error) {
        if (viewManagerModel.getState().equals("home")) {
            homeViewModel.firePropertyChanged("wishlistAddFail");
        }
        else if (viewManagerModel.getState().equals("wishlist")) {
            wishlistViewModel.firePropertyChanged("wishlistAddFail");
        }
    }
}
