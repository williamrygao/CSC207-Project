package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RatingTest {

    private Rating rating;

    @BeforeEach
    void setUp() {
        rating = new Rating("book123");
    }

    @Test
    void testConstructor_WithNullBookId() {
        assertThrows(IllegalArgumentException.class, () -> new Rating(null));
    }

    @Test
    void testConstructor_WithEmptyBookId() {
        assertThrows(IllegalArgumentException.class, () -> new Rating(""));
    }

    @Test
    void testAddRating_Valid() {
        rating.addRating(5);
        rating.addRating(8);
        assertEquals(2, rating.getRatingNumber());
    }

    @Test
    void testAddRating_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> rating.addRating(11));
        assertThrows(IllegalArgumentException.class, () -> rating.addRating(0));
    }

    @Test
    void testGetAverageRating_NoRatings() {
        assertEquals(0.0, rating.getAverageRating(), 0.001);
    }

    @Test
    void testGetAverageRating_WithRatings() {
        rating.addRating(5);
        rating.addRating(7);
        rating.addRating(10);
        assertEquals(7.33, rating.getAverageRating(), 0.01); // 5+7+10 = 22, 22/3 = 7.33
    }

    @Test
    void testGetRatings() {
        rating.addRating(5);
        rating.addRating(8);
        var ratingsList = rating.getRatings();
        assertEquals(2, ratingsList.size());
        // Verify that the list is a copy, not the original
        assertNotSame(ratingsList, rating.getRatings());
    }

    @Test
    void testGetRatingNumber() {
        assertEquals(0, rating.getRatingNumber());
        rating.addRating(6);
        assertEquals(1, rating.getRatingNumber());
    }

    @Test
    void testGetBookId() {
        assertEquals("book123", rating.getBookId());
    }
}
