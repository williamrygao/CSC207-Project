package interface_adapter.filter_by_genre;

import java.util.List;

import entity.listing.Listing;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import use_case.filter_by_genre.FilterByGenreOutputBoundary;
import use_case.filter_by_genre.FilterByGenreOutputData;

/**
 * The Presenter for the Filter Books by Genre Use Case.
 */
public class FilterByGenrePresenter implements FilterByGenreOutputBoundary {
    private final FilterByGenreViewModel filterByGenreViewModel;
    private final HomeViewModel homeViewModel;

    public FilterByGenrePresenter(FilterByGenreViewModel filterByGenreViewModel, HomeViewModel homeViewModel) {
        this.filterByGenreViewModel = filterByGenreViewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(FilterByGenreOutputData filterByGenreOutputData) {
        // Update the UI with the filtered listings
        final List<Listing> listings = filterByGenreOutputData.getListings();
        final FilterByGenreState state = filterByGenreViewModel.getState();
        state.setListings(listings);
        filterByGenreViewModel.setState(state);
        filterByGenreViewModel.firePropertyChanged("listings filtered");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Update the UI with the error message
        filterByGenreViewModel.firePropertyChanged("Error");
    }
}
