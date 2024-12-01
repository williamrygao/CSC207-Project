package use_case.filter_by_genre;

/**
 * Interface for the output boundary of the "Filter Books by Genre" use case.
 * Defines the method to handle presenting the filtered book listings.
 */
public interface FilterByGenreOutputBoundary {

    /**
     * Prepares and presents the filtered book listings.
     * This method is called to display the filtered data to the user or the next layer in the system.
     *
     * @param outputData the data containing the filtered listings
     */
    void presentFilteredListings(FilterByGenreOutputData outputData);
}
