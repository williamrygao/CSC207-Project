package entity;

package entity.listing;

import static org.junit.jupiter.api.Assertions.*;

import entity.listing.Listing;
import entity.listing.ListingIterator;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.book.Book;

import java.util.NoSuchElementException;

class ListingIteratorTest {
    private JSONObject jsonResponse;
    private ListingIterator listingIterator;

    @BeforeEach
    void setUp() {
        jsonResponse = new JSONObject();
        jsonResponse.put("listing1", new JSONObject()
                .put("bookID", "123")
                .put("title", "Effective Java")
                .put("authors", "Joshua Bloch")
                .put("genre", "Programming")
                .put("bookPrice", "45.99 USD")
                .put("listingPrice", "50.00 USD")
                .put("seller", "SellerA")
                .put("rating", 4.5f)
                .put("isAvailable", true));

        jsonResponse.put("listing2", new JSONObject()
                .put("bookID", "456")
                .put("title", "Clean Code")
                .put("authors", "Robert C. Martin")
                .put("genre", "Programming")
                .put("bookPrice", "35.99 USD")
                .put("listingPrice", "40.00 USD")
                .put("seller", "SellerB")
                .put("rating", 4.8f)
                .put("isAvailable", false));

        listingIterator = new ListingIterator(jsonResponse);
    }

    @Test
    void testConstructor() {
        assertNotNull(listingIterator);
    }

    @Test
    void testHasNext_True() {
        assertTrue(listingIterator.hasNext());
    }

    @Test
    void testHasNext_False() {
        listingIterator.next(); // First element
        listingIterator.next(); // Second element
        assertFalse(listingIterator.hasNext());
    }

    @Test
    void testNext_Success() {
        Listing listing = listingIterator.next();
        assertNotNull(listing);
        assertEquals("123", listing.getListingID());
        assertEquals("Effective Java", listing.getBook().getTitle());
        assertEquals("Joshua Bloch", listing.getBook().getAuthors());
        assertEquals("Programming", listing.getBook().getGenre());
        assertEquals("SellerA", listing.getSeller());
        assertEquals("50.00 USD", listing.getPrice());
        assertTrue(listing.isAvailable());
    }

    @Test
    void testNext_ThrowsNoSuchElementException() {
        listingIterator.next(); // First element
        listingIterator.next(); // Second element
        assertThrows(NoSuchElementException.class, () -> listingIterator.next());
    }

    @Test
    void testGetKey() {
        listingIterator.next();
        assertEquals("listing1", listingIterator.getKey());
        listingIterator.next();
        assertEquals("listing2", listingIterator.getKey());
    }
}

