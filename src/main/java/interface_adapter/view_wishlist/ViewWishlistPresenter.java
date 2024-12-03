package interface_adapter.view_wishlist;

import java.util.ArrayList;
import java.util.List;

import entity.Listing;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.remove_from_wishlist.WishlistState;
import interface_adapter.remove_from_wishlist.WishlistViewModel;
import use_case.view_wishlist.ViewWishlistOutputBoundary;
import use_case.view_wishlist.ViewWishlistOutputData;

/**
 * The Presenter for the View Wishlist Use Case.
 */
public class ViewWishlistPresenter implements ViewWishlistOutputBoundary {

    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WishlistViewModel wishlistViewModel;

    public ViewWishlistPresenter(final ViewManagerModel viewManagerModel, final HomeViewModel homeViewModel, final WishlistViewModel wishlistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.wishlistViewModel = wishlistViewModel;
    }

    @Override
    public void prepareSuccessView(ViewWishlistOutputData response) {
        final HomeState homeState = homeViewModel.getState();

        final WishlistState wishlistState = wishlistViewModel.getState();
        wishlistState.setUsername(homeState.getUsername());

        final List<Listing> wishlist = response.getWishlist();
        wishlistState.setWishlist(wishlist);

        wishlistViewModel.setState(wishlistState);
        wishlistViewModel.firePropertyChanged("wishlist");

        this.viewManagerModel.setState(wishlistViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(final String error) {

    }
}
