package use_case.filter_by_category;

/**
 * The interactor (use case) class for the "Filter Books by Genre" functionality.
 * Implements the business logic to filter books and coordinates data flow between
 * the input boundary, output boundary, and data access interface.
 */

public class FilterByGenreInteractor {

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

}