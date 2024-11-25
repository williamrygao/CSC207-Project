package use_case.sell;

import entity.Book;

/**
 * DAO for the Sell Use Case.
 */
public interface SellBookDataAccessInterface {
    /**
     * Checks if the given book exists.
     * @param bookID the bookID to look for
     * @return true if a book with the given book ID exists; false otherwise
     */
    boolean existsByBookID(String bookID);

    /**
     * Saves the book.
     * @param book the book to save
     */
    void save(Book book);

    /**
     * Gets the price of the book from Google API.
     * @param bookID the ID of the book
     * @return price of the book (or -1 if not available)
     */
    String getBookPrice(String bookID);
}
