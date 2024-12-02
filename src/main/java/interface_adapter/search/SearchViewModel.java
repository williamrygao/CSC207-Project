package interface_adapter.search;

import java.util.List;

import entity.listing.Listing;
import interface_adapter.ViewModel;

/**
 * The View Model for the Search View.
 */
public class SearchViewModel extends ViewModel<SearchState> {

    private List<Listing> searchResults;

    public SearchViewModel() {
        super("search");
        setState(new SearchState());
    }

    /**
     * Setter for search results.
     * @param results the list of listings to update search results
     */
    public void setSearchResults(List<Listing> results) {
        this.searchResults = results;
        firePropertyChanged("searchResults");
    }

    public List<Listing> getSearchResults() {
        return searchResults;
    }

}
