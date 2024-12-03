package use_case.filter_by_genre;

/**
 * The Input Data for the Genre Use Case.
 */
public class FilterByGenreInputData {

    private final String genre;

    /**
     * Constructor to initialize the genre to be used for filtering.
     *
     * @param genre the genre to filter by
     */
    public FilterByGenreInputData(String genre) {
        this.genre = genre;
    }

    /**
     * Getter for genre.
     *
     * @return the genre to filter by
     */
    public String getGenre() {
        return genre;
    }
}
