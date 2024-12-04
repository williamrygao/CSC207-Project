package use_case.filter_by_genre;

import java.util.List;

import entity.listing.Listing;

/**
 * The interactor (use case) class for the "Filter Books by Genre" functionality.
 * Implements the business logic to filter books and coordinates data flow between
 * the input boundary, output boundary, and data access interface.
 */
public class FilterByGenreInteractor implements FilterByGenreInputBoundary {

    private final FilterByGenreDataAccessInterface dataAccess;
    private final FilterByGenreOutputBoundary filterByGenrePresenter;

    /**
     * Constructs a FilterByGenreInteractor with the specified data access and output boundary.
     *
     * @param filterByGenreDataAccessInterface    the interface for accessing book data
     * @param filterByGenreOutputBoundary the interface for presenting filtered data
     */
    public FilterByGenreInteractor(FilterByGenreDataAccessInterface filterByGenreDataAccessInterface,
                                   FilterByGenreOutputBoundary filterByGenreOutputBoundary) {
        this.dataAccess = filterByGenreDataAccessInterface;
        this.filterByGenrePresenter = filterByGenreOutputBoundary;
    }

    /**
     * Handles the use case of filtering books by genre.
     *
     * @param inputData the data containing the genre to filter by
     */

    @Override
    public void execute(FilterByGenreInputData inputData) {
        final String genre = inputData.getGenre();

        // Input validation: If genre is empty or null, prepare a failure view
        if (genre == null || genre.trim().isEmpty()) {
            this.filterByGenrePresenter.prepareFailView("Genre cannot be empty or null.");
        }

        // Retrieve filtered listings from the data access layer
        final List<Listing> Listings = dataAccess.getListingsByGenre(genre);

        // If no listings are found, prepare a failure view
        if (Listings.isEmpty()) {
            this.filterByGenrePresenter.prepareFailView("No listings found for the specified genre.");
        }

        // Prepare the success view with the filtered listings
        final FilterByGenreOutputData outputData = new FilterByGenreOutputData(Listings);
        this.filterByGenrePresenter.prepareSuccessView(outputData);
    }
}
