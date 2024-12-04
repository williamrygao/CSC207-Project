package entity;
package entity.listing;

import static org.junit.jupiter.api.Assertions.*;

import entity.book.Book;
import entity.listing.Listing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListingTest {
    private Listing listing1;
    private Listing listing2;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        book1 = new Book("123", "Effective Java", "Joshua Bloch", "Programming", "45.99 USD");
        book2 = new Book("456", "Clean Code", "Robert C. Martin", "Programming", "35.99 USD");

        listing1 = new Listing("1", book1, "45.99", "SellerA", true);
        listing2 = new Listing("2", book2, "35.99", "SellerB", false);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("1", listing1.getListingID());
        assertEquals(book1, listing1.getBook());
        assertEquals("45.99", listing1.getPrice());
        assertEquals("SellerA", listing1.getSeller());
        assertTrue(listing1.isAvailable());
    }

    @Test
    void testSetters() {
        listing1.setListingID("3");
        listing1.setBook(book2);
        listing1.setPrice("50.00");
        listing1.setSeller("SellerC");
        listing1.setAvailable(false);

        assertEquals("3", listing1.getListingID());
        assertEquals(book2, listing1.getBook());
        assertEquals("50.00", listing1.getPrice());
        assertEquals("SellerC", listing1.getSeller());
        assertFalse(listing1.isAvailable());
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(listing1.equals(listing1));
    }

    @Test
    void testEquals_NullObject() {
        assertFalse(listing1.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        assertFalse(listing1.equals("Some String"));
    }

    @Test
    void testEquals_DifferentBooks() {
        assertFalse(listing1.equals(listing2));
    }

    @Test
    void testEquals_SameBookAndSeller() {
        Listing listing3 = new Listing("3", book1, "50.00", "SellerA", true);
        assertTrue(listing1.equals(listing3));
    }

    @Test
    void testEquals_DifferentSeller() {
        Listing listing4 = new Listing("4", book1, "50.00", "SellerB", true);
        assertFalse(listing1.equals(listing4));
    }

    @Test
    void testEquals_DifferentAvailability() {
        Listing listing5 = new Listing("5", book1, "45.99", "SellerA", false);
        assertFalse(listing1.equals(listing5));
    }
}
