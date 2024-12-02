package use_case.filter_by_genre;

public class FilterByGenreInputData {

    private final String genre;

    /**
     * Constructor to initialize the genre to be used for filtering.
     *
     * @param genre the genre to filter by
     * @throws IllegalArgumentException if the genre is null or empty
     */
    public FilterByGenreInputData(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be empty.");
        }
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
