package use_case.filter_by_genre;

/**
 * Interface for the output boundary of the "Filter Books by Genre" use case.
 * Defines the method to handle presenting the filtered book listings.
 */
public interface FilterByGenreOutputBoundary {

    /**
     * Prepares and presents the filtered book listings.
     *
     * @param outputData the data containing the filtered listings
     */
    void presentFilteredListings(FilterByGenreOutputData outputData);
}
