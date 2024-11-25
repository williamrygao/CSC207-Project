package use_case.filter_by_rating;

import java.util.List;

import entity.Book;
/**
 * Output Data for the Filter By Rating Use Case.
 */

public class FilterByRatingOutputData {
    private final List<Book> books;

    public FilterByRatingOutputData(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}