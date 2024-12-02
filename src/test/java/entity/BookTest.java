package entity.book;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testConstructorWithRating() {
        Book book = new Book("1", "Title", "Author", "Genre", "$10", 4.5f);
        assertEquals("1", book.getBookId());
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthors());
        assertEquals("Genre", book.getGenre());
        assertEquals("$10", book.getPrice());
        assertEquals(4.5f, book.getRating());
    }

    @Test
    void testConstructorWithoutRating() {
        Book book = new Book("2", "Another Title", "Another Author", "Another Genre", "$15");
        assertEquals("2", book.getBookId());
        assertEquals("Another Title", book.getTitle());
        assertEquals("Another Author", book.getAuthors());
        assertEquals("Another Genre", book.getGenre());
        assertEquals("$15", book.getPrice());
        assertEquals(0, book.getRating());
    }

    @Test
    void testSettersAndGetters() {
        Book book = new Book("3", "Title", "Author", "Genre", "$10", 3.0f);

        book.setBookId("4");
        assertEquals("4", book.getBookId());

        book.setTitle("Updated Title");
        assertEquals("Updated Title", book.getTitle());

        book.setAuthors("Updated Author");
        assertEquals("Updated Author", book.getAuthors());

        book.setGenre("Updated Genre");
        assertEquals("Updated Genre", book.getGenre());

        book.setRating(4.0f);
        assertEquals(4.0f, book.getRating());
    }

    @Test
    void testAddRating() {
        Book book = new Book("1", "Title", "Author", "Genre", "$10", 4.5f);
        book.addRating(5);
        book.addRating(3);

        List<Integer> expectedRatings = Arrays.asList(5, 3);
        assertEquals(expectedRatings, book.getRatings());
    }

    @Test
    void testSetRatings() {
        Book book = new Book("1", "Title", "Author", "Genre", "$10", 4.5f);
        List<Integer> ratings = Arrays.asList(4, 2, 5);
        book.setRatings(ratings);

        assertEquals(ratings, book.getRatings());
    }

    @Test
    void testGetAverageRating() {
        Book book = new Book("1", "Title", "Author", "Genre", "$10", 4.5f);

        assertEquals(0.0, book.getAverageRating()); // No ratings

        book.addRating(5);
        book.addRating(3);
        book.addRating(4);
        assertEquals(4.0, book.getAverageRating());
    }

    @Test
    void testEqualsAndHashCode() {
        Book book1 = new Book("1", "Title", "Author", "Genre", "$10");
        Book book2 = new Book("1", "Another Title", "Another Author", "Another Genre", "$15");
        Book book3 = new Book("2", "Title", "Author", "Genre", "$10");

        assertEquals(book1, book2); // Same bookId
        assertNotEquals(book1, book3); // Different bookId
        assertEquals(book1.hashCode(), book2.hashCode());
        assertNotEquals(book1.hashCode(), book3.hashCode());
    }

    @Test
    void testToString() {
        Book book = new Book("1", "Title", "Author", "Genre", "$10");
        String expectedString = "Book{bookId='1', title='Title', authors='Author', genre='Genre'}";
        assertEquals(expectedString, book.toString());
    }

    @Test
    void testUpdateRating() {
        Book book = new Book("1", "Title", "Author", "Genre", "$10", 4.5f);
        book.updateRating(3);
        assertEquals(3.0f, book.getRating());
    }



}
