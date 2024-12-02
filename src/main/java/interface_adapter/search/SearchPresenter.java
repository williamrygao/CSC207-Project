package interface_adapter.search;

import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

/**
 * The Presenter for the Search Use Case.
 */
public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;

    public SearchPresenter(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData outputData) {
        // Update the SearchState with the matching listings
        final SearchState searchState = searchViewModel.getState();
        searchState.setListings(outputData.getListings());
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged("Search Results Updated");
    }

    @Override
    public void prepareFailView(String error) {
        // Handle failure: Show error message in view
        searchViewModel.firePropertyChanged("No Search Results Found");
    }
}
