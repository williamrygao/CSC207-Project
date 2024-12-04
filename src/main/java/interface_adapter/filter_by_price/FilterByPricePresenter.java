package interface_adapter.filter_by_price;

import java.util.List;

import entity.listing.Listing;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import use_case.filter_by_price.FilterByPriceOutputBoundary;
import use_case.filter_by_price.FilterByPriceOutputData;

/**
 * The Presenter for the Filter By Price Use Case.
 */
public class FilterByPricePresenter implements FilterByPriceOutputBoundary {

    // All the relevant view models as attributes
    private final FilterByPriceViewModel filterByPriceViewModel;
    private final HomeViewModel homeViewModel;

    // Initialize the class with the view models
    public FilterByPricePresenter(FilterByPriceViewModel filterByPriceViewModel, HomeViewModel homeViewModel) {
        this.filterByPriceViewModel = filterByPriceViewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(FilterByPriceOutputData filterByPriceOutputData) {
        // extract data from output data object
        final List<Listing> listings = filterByPriceOutputData.getListings();

        // change listings in home state
        final FilterByPriceState state = filterByPriceViewModel.getState();
        state.setListings(listings);
        filterByPriceViewModel.setState(state);
        filterByPriceViewModel.firePropertyChanged("listings filtered");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // prompt the Filter By Price view to display an error message
        filterByPriceViewModel.firePropertyChanged("error");
    }
}
