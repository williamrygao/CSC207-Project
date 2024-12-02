package interface_adapter.wishlist.view_wishlist;

import java.util.List;

import entity.listing.Listing;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.wishlist.WishlistState;
import interface_adapter.wishlist.WishlistViewModel;
import use_case.wishlist.view_wishlist.ViewWishlistOutputBoundary;
import use_case.wishlist.view_wishlist.ViewWishlistOutputData;

/**
 * The Presenter for the View Wishlist Use Case.
 */
public class ViewWishlistPresenter implements ViewWishlistOutputBoundary {

    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WishlistViewModel wishlistViewModel;

    public ViewWishlistPresenter(final ViewManagerModel viewManagerModel,
                                 final HomeViewModel homeViewModel,
                                 final WishlistViewModel wishlistViewModel) {
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
        wishlistViewModel.firePropertyChanged();

        this.viewManagerModel.setState(wishlistViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(final String error) {
        homeViewModel.firePropertyChanged("viewWishlistError");
    }
}
