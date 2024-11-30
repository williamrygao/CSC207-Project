package use_case.filter_by_category;

import java.util.List;

import entity.book.Book;

/**
 * Interface for data access operations related to filtering books by genre.
 * Defines the methods required to interact with the database or data source.
 */

public class FilterByGenreDataAccessInterface {

    /**
     * Retrieves a list of books filtered by the specified genre.
     *
     * @param genre the genre to filter books by
     * @return a list of books matching the given genre
     * @throws UnsupportedOperationException if the method is not implemented
     */

    List<Book> getBooksByGenre(String genre) {

        throw new UnsupportedOperationException("Method not implemented");

    }

}
