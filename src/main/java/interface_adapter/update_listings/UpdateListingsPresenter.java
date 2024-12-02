package interface_adapter.update_listings;

import java.util.List;

import entity.listing.Listing;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import use_case.update_listings.UpdateListingsOutputBoundary;
import use_case.update_listings.UpdateListingsOutputData;

/**
 * The Presenter for the Update Listings Use Case.
 */
public class UpdateListingsPresenter implements UpdateListingsOutputBoundary {

    private final HomeViewModel homeViewModel;

    public UpdateListingsPresenter(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(UpdateListingsOutputData response) {
        final HomeState homeState = homeViewModel.getState();
        homeState.setUsername(response.getUsername());

        final List<Listing> listings = response.getListings();
        homeState.setListings(listings);

        final List<Listing> wishlist = response.getWishlist();
        homeState.setWishlist(wishlist);

        this.homeViewModel.setState(homeState);
        this.homeViewModel.firePropertyChanged("updateTable");
    }

    @Override
    public void prepareFailView(String error) {
    }
}
