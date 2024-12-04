package entity.book;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import data_access.GoogleBooksApi;

class BookFactoryTest {
    private BookFactory bookFactory;
    private GoogleBooksApi mockApi;

    @BeforeEach
    void setUp() {
        mockApi = Mockito.mock(GoogleBooksApi.class);
        bookFactory = new BookFactory() {
            @Override
            protected GoogleBooksApi getGoogleBooksApi() {
                return mockApi;
            }
        };
    }

    @Test
    void testCreateBook_AllFieldsPresent() {
        // Mock JSON response
        String jsonResponse = """
        {
            "volumeInfo": {
                "title": "Effective Java",
                "authors": ["Joshua Bloch"],
                "description": "A guide to Java programming best practices.",
                "categories": ["Programming", "Software Development"]
            },
            "saleInfo": {
                "retailPrice": {
                    "amount": 45.99,
                    "currencyCode": "USD"
                }
            }
        }""";
        when(mockApi.getBookByVolumeId("123")).thenReturn(jsonResponse);

        Book book = bookFactory.createBook("123");

        assertNotNull(book);
        assertEquals("123", book.getBookId());
        assertEquals("Effective Java", book.getTitle());
        assertEquals("Joshua Bloch", book.getAuthors());
        assertEquals("Programming, Software Development", book.getGenre());
        assertEquals("Price: 45.99 USD", book.getPrice());
    }

    @Test
    void testCreateBook_MissingFields() {
        // Mock JSON response with missing fields
        String jsonResponse = """
        {
            "volumeInfo": {
                "title": "Book Without Author or Categories"
            }
        }""";
        when(mockApi.getBookByVolumeId("456")).thenReturn(jsonResponse);

        Book book = bookFactory.createBook("456");

        assertNotNull(book);
        assertEquals("456", book.getBookId());
        assertEquals("Book Without Author or Categories", book.getTitle());
        assertEquals("Unknown Author", book.getAuthors());
        assertEquals("Unknown Genre", book.getGenre());
        assertEquals("Price information not available.", book.getPrice());
    }

    @Test
    void testCreateBook_NullResponse() {
        when(mockApi.getBookByVolumeId("789")).thenReturn(null);

        Book book = bookFactory.createBook("789");

        assertNull(book);
    }

    @Test
    void testCreateBook_NoPriceInfo() {
        // Mock JSON response with no price info
        String jsonResponse = """
        {
            "volumeInfo": {
                "title": "Free Book",
                "authors": ["Author A"],
                "categories": ["Fiction"]
            }
        }""";
        when(mockApi.getBookByVolumeId("101")).thenReturn(jsonResponse);

        Book book = bookFactory.createBook("101");

        assertNotNull(book);
        assertEquals("101", book.getBookId());
        assertEquals("Free Book", book.getTitle());
        assertEquals("Author A", book.getAuthors());
        assertEquals("Fiction", book.getGenre());
        assertEquals("Price information not available.", book.getPrice());
    }

    @Test
    void testCreateBook_EmptyCategoriesAndAuthors() {
        // Mock JSON response with empty arrays for authors and categories
        String jsonResponse = """
        {
            "volumeInfo": {
                "title": "Another Book",
                "authors": [],
                "categories": []
            }
        }""";
        when(mockApi.getBookByVolumeId("202")).thenReturn(jsonResponse);

        Book book = bookFactory.createBook("202");

        assertNotNull(book);
        assertEquals("202", book.getBookId());
        assertEquals("Another Book", book.getTitle());
        assertEquals("Unknown Author", book.getAuthors());
        assertEquals("Unknown Genre", book.getGenre());
        assertEquals("Price information not available.", book.getPrice());
    }
}
