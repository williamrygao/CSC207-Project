package interface_adapter.search;

import java.awt.*;

import javax.swing.*;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

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

        return "Search Completed Successfully!";
    }

    /**
     * Executes the Search Use Case to display messages (error/informational messages).
     * @param message the error message to display to user
     * @param center where the panel will be centered
     * @param title title of panel
     */
    public void message(Component center, String message, String title) {
        if ("Error".equals(title)) {
            JOptionPane.showMessageDialog(center, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(center, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
