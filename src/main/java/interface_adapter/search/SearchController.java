package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import javax.swing.*;
import java.awt.*;

/**
 * Controller for the Search Use Case.
 */
public class SearchController {
    private final SearchInputBoundary userSearchUseCaseInteractor;

    public SearchController(SearchInputBoundary userSearchUseCaseInteractor) {
        this.userSearchUseCaseInteractor = userSearchUseCaseInteractor;
    }

    /**
     * Get search result method for Search use case.
     * @param username the user's identification
     * @param bookID the book ID
     * @param authors the list of authors
     * @param bookTitle the book's title
     * @param price the price of the book
     * @return a list of books that match the search query
     */
    public String getSearchResults(String username, String bookID, String authors, String bookTitle, String price) {
        final SearchInputData searchInputData = new SearchInputData(username, bookID, authors, bookTitle, price);

        userSearchUseCaseInteractor.execute(searchInputData);

        return "Error: not implemented";
    }

    /**
     * Executes the Search Use Case for errors.
     * @param errorMessage the error message to display to user
     * @param center where the panel will be centered
     */
    public void error(Component center, String errorMessage) {
        JOptionPane.showMessageDialog(center, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
