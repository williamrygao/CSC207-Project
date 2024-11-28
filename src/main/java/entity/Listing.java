package entity;

import java.io.Serializable;

/**
 * A listing of a book.
 */
public class Listing implements Serializable {

    private String listingID;
    private Book book;
    private String price;
    private String seller;
    private boolean isAvailable;

    // Constructor, getters, and setters
    public Listing(String listingID, Book book, String price, String seller, boolean isAvailable) {
        this.listingID = listingID;
        this.book = book;
        this.price = price;
        this.seller = seller;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public String getListingID() {
        return listingID;
    }

    public void setListingID(String listingID) {
        this.listingID = listingID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
