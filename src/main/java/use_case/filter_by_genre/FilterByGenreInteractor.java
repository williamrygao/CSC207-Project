package use_case.filter_by_genre;

import java.util.List;

import entity.Listing;

/**
 * The interactor (use case) class for the "Filter Books by Genre" functionality.
 * Implements the business logic to filter books and coordinates data flow between
 * the input boundary, output boundary, and data access interface.
 */

public class FilterByGenreInteractor implements FilterByGenreInputBoundary {

    /**
     * Interface for accessing book data.
     */
    private final FilterByGenreDataAccessInterface dataAccess;

    /**
     * Interface for presenting the filtered book data to the output boundary.
     */
    private final FilterByGenreOutputBoundary outputBoundary;

    /**
     * Constructs a FilterByGenreInteractor with the specified data access and output boundary.
     *
     * @param dataAccess     the interface for accessing book data
     * @param outputBoundary the interface for presenting filtered data
     */
    public FilterByGenreInteractor(FilterByGenreDataAccessInterface dataAccess,
                                   FilterByGenreOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }
    /**
     * Handles the use case of filtering books by genre.
     *
     * @param inputData the data containing the genre to filter by
     */

    @Override
    public void filterByGenre(FilterByGenreInputData inputData) {
        // Extract the genre from the input data
        final String genre = inputData.getGenre();

        // Retrieve the listings from the data access layer
        final List<Listing> filteredListings = dataAccess.getListingsByGenre(genre);

        // Prepare the output data
        final FilterByGenreOutputData outputData = new FilterByGenreOutputData(filteredListings);

        // Send the filtered listings to the output boundary
        outputBoundary.presentFilteredListings(outputData);
    }

}
