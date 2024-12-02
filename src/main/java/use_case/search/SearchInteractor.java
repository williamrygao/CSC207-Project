package use_case.search;

import java.util.ArrayList;
import java.util.List;

import data_access.FirebaseListingDataAccessObject;
import entity.listing.Listing;

/**
 * The Search Interactor.
 */
public class SearchInteractor implements SearchInputBoundary {
    private final FirebaseListingDataAccessObject bookDataAccessObject;
    private final SearchOutputBoundary userPresenter;

    public SearchInteractor(FirebaseListingDataAccessObject bookDataAccessObject,
                            SearchOutputBoundary searchOutputBoundary) {
        this.userPresenter = searchOutputBoundary;
        this.bookDataAccessObject = bookDataAccessObject;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        final String seller = searchInputData.getUsername();
        final String bookID = searchInputData.getBookID();
        final String authors = searchInputData.getAuthors();
        final String title = searchInputData.getTitle();
        final String price = searchInputData.getPrice();

        List<Listing> matchingListings = new ArrayList<>();

        final List<Listing> allListings = bookDataAccessObject.getListings();

        // Perform partial search and filter listings based on any of the fields
        for (Listing listing : allListings) {
            if (listing.getBook().getBookId().contains(bookID)
                    || listing.getBook().getAuthors().contains(authors)
                    || listing.getBook().getTitle().contains(title)
                    || listing.getPrice().contains(price)) {
                matchingListings.add(listing);
            }
        }

        // Prepare the output data to pass to the presenter
        if (!matchingListings.isEmpty()) {
            SearchOutputData searchOutputData = new SearchOutputData(seller, matchingListings, false);
            userPresenter.prepareSuccessView(searchOutputData);
        }
        else {
            // Handle failure: No results found
            userPresenter.prepareFailView("No matching results found");
        }
    }
}
