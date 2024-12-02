package interface_adapter.filter_by_rating;

import entity.listing.Listing;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import use_case.filter_by_rating.FilterByRatingOutputBoundary;
import use_case.filter_by_rating.FilterByRatingOutputData;

import java.util.List;

/**
 * The Presenter for the Filter By Rating Use Case.
 */
public class FilterByRatingPresenter implements FilterByRatingOutputBoundary {

    // All the relevant view models as attributes
    private final FilterByRatingViewModel filterByRatingViewModel;
    private final HomeViewModel homeViewModel;

    // Initialize the class with the view models
    public FilterByRatingPresenter(FilterByRatingViewModel filterByRatingViewModel, HomeViewModel homeViewModel) {
        this.filterByRatingViewModel = filterByRatingViewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(FilterByRatingOutputData filterByRatingOutputData) {
        // extract data from output data object
        final List<Listing> listings = filterByRatingOutputData.getListings();

        // change listings in home state
        final HomeState homeState = homeViewModel.getState();
        homeState.setListings(listings);
        this.homeViewModel.setState(homeState);
        this.homeViewModel.firePropertyChanged("listing");

        // prompt the Filter By Rating view to go back to home
        filterByRatingViewModel.firePropertyChanged("listings filtered");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // prompt the Filter By Rating view to display an error message
        filterByRatingViewModel.firePropertyChanged("error");
    }
}
