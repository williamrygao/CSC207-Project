package interface_adapter.search;

import java.util.ArrayList;
import java.util.List;

import entity.listing.Listing;

/**
 * The State information representing book characterization for search.
 */
public class SearchState {
    private String username = "";
    private String password = "";
    private String bookID = "";
    private String authors;
    private String title;
    private String price;
    private List<Listing> listings;

    private List<Listing> wishlist = new ArrayList<>();

    public SearchState() {
        listings = new ArrayList<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getAuthors() {
        return authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setBookID(String book) {
        this.bookID = book;
    }

    public String getBookID() {
        return bookID;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setWishlist(List<Listing> wishlist) {
        this.wishlist = wishlist;
    }

    public List<Listing> getWishlist() {
        return wishlist;
    }
}
