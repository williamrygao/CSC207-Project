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

        final List<Listing> matchingListings = new ArrayList<>();

        final List<Listing> allListings = bookDataAccessObject.getListings();

        // Perform search and filter listings based on any of the fields
        for (Listing listing : allListings) {

            // Safely handle null and trim input fields before comparison
            String bookIdToSearch = "";
            if (bookID != null) {
                bookIdToSearch = bookID.trim().toLowerCase();
            }

            String authorsToSearch = "";
            if (authors != null) {
                authorsToSearch = authors.trim().toLowerCase();
            }

            String titleToSearch = "";
            if (title != null) {
                titleToSearch = title.trim().toLowerCase();
            }

            String priceToSearch = "";
            if (price != null) {
                priceToSearch = price.trim().toLowerCase();
            }

            boolean matches = false;

            // Check if bookID is not empty and matches
            if (!bookIdToSearch.isEmpty() && listing.getBook().getBookId().toLowerCase().contains(bookIdToSearch)) {
                matches = true;
            }

            // Check if authors is not empty and matches
            if (!authorsToSearch.isEmpty() && listing.getBook().getAuthors().toLowerCase().contains(authorsToSearch)) {
                matches = true;
            }

            // Check if title is not empty and matches
            if (!titleToSearch.isEmpty() && listing.getBook().getTitle().toLowerCase().contains(titleToSearch)) {
                matches = true;
            }

            // Check if price is not empty and matches
            if (!priceToSearch.isEmpty() && listing.getPrice().toLowerCase().contains(priceToSearch)) {
                matches = true;
            }

            // If there's a match, add the listing to the filtered list
            if (matches) {
                matchingListings.add(listing);
            }
        }

        // Pass the filtered listings to the presenter
        if (!matchingListings.isEmpty()) {
            final SearchOutputData searchOutputData = new SearchOutputData(seller, matchingListings, false);
            userPresenter.prepareSuccessView(searchOutputData);
        }
        else {
            // No results found
            userPresenter.prepareFailView("No matching results found");
        }
    }
}

