package use_case.filter_by_genre;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.listing.Listing;

/**
 * The interactor (use case) class for the "Filter Books by Genre" functionality.
 * Implements the business logic to filter books and coordinates data flow between
 * the input boundary, output boundary, and data access interface.
 */
public class FilterByGenreInteractor implements FilterByGenreInputBoundary {

    private static final Logger LOGGER = Logger.getLogger(FilterByGenreInteractor.class.getName());

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

        // Validation: genre should not be null or empty
        if (genre == null || genre.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Received empty or null genre.");
            throw new IllegalArgumentException("Genre cannot be empty or null.");
        }

        // Retrieve the listings from the data access layer
        final List<Listing> filteredListings;
        try {
            filteredListings = dataAccess.getListingsByGenre(genre);
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving listings by genre: " + genre, ex);
            throw new RuntimeException("Failed to retrieve listings.", ex);
        }

        // Prepare the output data
        final FilterByGenreOutputData outputData = new FilterByGenreOutputData(filteredListings);

        // Send the filtered listings to the output boundary
        outputBoundary.presentFilteredListings(outputData);
    }
}
