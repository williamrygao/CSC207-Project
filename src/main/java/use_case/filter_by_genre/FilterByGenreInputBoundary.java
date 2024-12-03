package use_case.filter_by_genre;

/**
 * Interface for the input boundary of the "Filter Books by Genre" use case.
 * This interface defines the method to be called for filtering books by genre.
 */
public interface FilterByGenreInputBoundary {

    /**
     * Executes the Filter Books by Genre use case.
     *
     * @param inputData the input data containing the genre to filter by
     */
    void execute(FilterByGenreInputData inputData);
}
