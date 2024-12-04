package interface_adapter.wishlist.add_to_wishlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.wishlist.WishlistViewModel;
import use_case.wishlist.add_to_wishlist.AddToWishlistOutputBoundary;
import use_case.wishlist.add_to_wishlist.AddToWishlistOutputData;

/**
 * The Presenter for the Add To Wishlist Use Case.
 */
public class AddToWishlistPresenter implements AddToWishlistOutputBoundary {
    private final WishlistViewModel wishlistViewModel;
    private final HomeViewModel homeViewModel;
    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddToWishlistPresenter(WishlistViewModel wishlistViewModel, HomeViewModel homeViewModel,
                                  SearchViewModel searchViewModel, ViewManagerModel viewManagerModel) {
        this.wishlistViewModel = wishlistViewModel;
        this.homeViewModel = homeViewModel;
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddToWishlistOutputData addToWishlistOutputData) {
        final String addedToWishlist = "addedToWishlist";
        if (viewManagerModel.getState().equals("home")) {
            homeViewModel.firePropertyChanged(addedToWishlist);
        }
        else if (viewManagerModel.getState().equals("wishlist")) {
            wishlistViewModel.firePropertyChanged(addedToWishlist);
        }
        else if (viewManagerModel.getState().equals("search")) {
            searchViewModel.firePropertyChanged(addedToWishlist);
        }
    }

    @Override
    public void prepareFailView(String error) {
        final String wishlistAddFail = "wishlistAddFail";
        if (viewManagerModel.getState().equals("home")) {
            homeViewModel.firePropertyChanged(wishlistAddFail);
        }
        else if (viewManagerModel.getState().equals("wishlist")) {
            wishlistViewModel.firePropertyChanged(wishlistAddFail);
        }
        else if (viewManagerModel.getState().equals("search")) {
            searchViewModel.firePropertyChanged(wishlistAddFail);
        }
    }
}
