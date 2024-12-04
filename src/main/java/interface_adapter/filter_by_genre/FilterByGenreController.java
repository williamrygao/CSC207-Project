package interface_adapter.filter_by_genre;

import java.awt.*;

import javax.swing.*;

import use_case.filter_by_genre.FilterByGenreInputBoundary;
import use_case.filter_by_genre.FilterByGenreInputData;

/**
 * Controller for the Filter Books by Genre Use Case.
 */
public class FilterByGenreController {
    private final FilterByGenreInputBoundary filterByGenreInteractor;

    public FilterByGenreController(FilterByGenreInputBoundary filterByGenreInteractor) {
        this.filterByGenreInteractor = filterByGenreInteractor;
    }

    /**
     * Execute the Filter Books by Genre Use Case.
     * @param genre genre to filter listings by
     */
    public void execute(String genre) {
        final FilterByGenreInputData filterByGenreInputData = new FilterByGenreInputData(genre);
        filterByGenreInteractor.execute(filterByGenreInputData);
    }

    /**
     * Executes error messages for the filter by price use case.
     * @param errorMessage the error message to display to user
     * @param center where the panel will be centered
     */
    public void error(Component center, String errorMessage) {
        JOptionPane.showMessageDialog(center, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
