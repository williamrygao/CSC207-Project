package entity;

import static org.junit.jupiter.api.Assertions.*;

import entity.listing.Listing;
import entity.user.CommonUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommonUserTest {

    private CommonUser user;
    private Listing listing;

    @BeforeEach
    void setUp() {
        user = new CommonUser("John Doe", "password123");
        listing = new Listing("123", null, "15.99", "John Seller", true); // Use a mock or simplified listing for testing
    }

    @Test
    void testConstructor() {
        assertEquals("John Doe", user.getName());
        assertEquals("password123", user.getPassword());
        assertNotNull(user.getWishlist());
        assertTrue(user.getWishlist().isEmpty());
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", user.getName());
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testGetWishlist() {
        assertNotNull(user.getWishlist());
        assertTrue(user.getWishlist().isEmpty());
    }

    @Test
    void testAddToWishlist() {
        user.addToWishlist(listing);
        assertEquals(1, user.getWishlist().size());
        assertTrue(user.getWishlist().contains(listing));
    }


}

