package use_case.search;

import entity.listing.Listing;

/**
 * DAO for the Sell Use Case.
 */
public interface SearchBookDataAccessInterface {
    /**
     * Checks if the given book exists.
     * @param bookID the bookID to look for
     * @return true if a book with the given book ID exists; false otherwise
     */
    boolean existsByBookID(String bookID);

    /**
     * Saves the listing.
     * @param listing the book to save
     */
    void save(Listing listing);
}
