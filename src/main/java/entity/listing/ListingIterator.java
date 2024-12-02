package entity.listing;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.json.JSONObject;

import entity.book.Book;

/**
 * Iterator for Listing objects represented in a JSONObject.
 */
public class ListingIterator implements Iterator<Listing> {
    private final Iterator<String> keysIterator;
    private final JSONObject jsonResponse;
    private String key;

    public ListingIterator(JSONObject jsonResponse) {
        this.jsonResponse = jsonResponse;
        this.keysIterator = jsonResponse.keys();
    }

    @Override
    public boolean hasNext() {
        return keysIterator.hasNext();
    }

    @Override
    public Listing next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        key = keysIterator.next();
        final JSONObject jsonListing = jsonResponse.getJSONObject(key);

        final String bookID = jsonListing.getString("bookID");
        final String title = jsonListing.getString("title");
        final String authors = jsonListing.getString("authors");
        final String genre = jsonListing.getString("genre");
        final String bookPrice = jsonListing.getString("bookPrice");
        final String listingPrice = jsonListing.getString("listingPrice");
        final String seller = jsonListing.getString("seller");
        final float rating = jsonListing.getFloat("rating");
        final boolean isAvailable = jsonListing.getBoolean("isAvailable");

        final Book book = new Book(bookID, title, authors, genre, bookPrice, rating);
        return new Listing(bookID, book, listingPrice, seller, isAvailable);
    }

    public String getKey() {
        return key;
    }
}
