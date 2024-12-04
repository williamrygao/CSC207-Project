package interface_adapter.to_filter_by_genre;

import use_case.to_filter_by_genre.ToFilterByGenreInputBoundary;

/**
 * Controller for the Filter Books by Genre Use Case.
 */
public class ToFilterByGenreController {
    private final ToFilterByGenreInputBoundary toFilterByGenreInteractor;

    public ToFilterByGenreController(final ToFilterByGenreInputBoundary toFilterByGenreInteractor) {
        this.toFilterByGenreInteractor = toFilterByGenreInteractor;
    }

    /**
     * Execute the Filter Books by Genre Use Case.
     *
     */
    public void execute() {
        toFilterByGenreInteractor.execute();
    }
}
