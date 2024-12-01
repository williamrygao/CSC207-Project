package use_case.filter_by_genre;

/**
 * The input data for the "Filter Books by Genre" use case.
 * This class contains the genre used to filter the books.
 */
public class FilterByGenreInputData {

    private final String genre;

    /**
     * Constructor to initialize the genre to be used for filtering.
     * Essential for initalizing
     * @param genre the genre to filter by
     *
     */

    public FilterByGenreInputData(String genre) {
        this.genre = genre;
    }

    /**
     * Returns the genre for filtering.
     *
     * @return the genre to filter by
     */

    public String getGenre() {
        return genre;
    }
}
