package use_case.sell;

import entity.listing.Listing;

/**
 * DAO for the Sell Use Case.
 */
public interface SellListingDataAccessInterface {
    /**
     * Checks if the given book exists.
     * @param bookID the bookID to look for
     * @param seller user selling the book
     * @return true if a book with the given book ID exists; false otherwise
     */
    boolean exists(String bookID, String seller);

    /**
     * Saves the listing.
     * @param listing the book to save
     */
    void save(Listing listing);
}
