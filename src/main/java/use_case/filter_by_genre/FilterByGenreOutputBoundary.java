package use_case.filter_by_genre;

/**
 * Interface for the output boundary of the "Filter Books by Genre" use case.
 * Defines the methods to handle presenting the filtered book listings.
 */
public interface FilterByGenreOutputBoundary {

    /**
     * Successful execution of the use case.
     * This method is called to display the filtered listings to the user or the next layer in the system.
     *
     * @param outputData the data containing the filtered listings
     */
    void prepareSuccessView(FilterByGenreOutputData outputData);

    /**
     * Use case failed.
     * This method is called to notify failure to the user or the next layer in the system.
     *
     * @param errorMessage the message to display
     */
    void prepareFailView(String errorMessage);
}