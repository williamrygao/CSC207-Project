package interface_adapter.search;

import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

/**
 * The Presenter for the Search Use Case.
 */
public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final HomeViewModel homeViewModel;

    public SearchPresenter(SearchViewModel searchViewModel, HomeViewModel homeViewModel) {
        this.searchViewModel = searchViewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData outputData) {
        // Add Listing to Home View
        final HomeState homeState = homeViewModel.getState();
        homeState.addListing(outputData.getListing());
        this.homeViewModel.setState(homeState);
        this.homeViewModel.firePropertyChanged("listing");

        // Pop-up in Search View.
        searchViewModel.firePropertyChanged("Search Generated");
    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }
}
