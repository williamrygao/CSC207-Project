package use_case.sell;

import entity.Book;
import entity.Listing;

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
     * Saves the listing.
     * @param listing the book to save
     */
    void save(Listing listing);

    /**
     * Gets the price of the book from Google API.
     * @param bookID the ID of the book
     * @return price of the book with currency
     */
    String getBookPrice(String bookID);

    /**
     * Gets the user listed selling price of chosen book.
     * @param SellingPrice the listed Selling price of book
     * @param bookID the ID of the book
     * @return a message to the user telling them their book has been listed
     */
    String getUserSellingListing(String SellingPrice, String bookID);
}