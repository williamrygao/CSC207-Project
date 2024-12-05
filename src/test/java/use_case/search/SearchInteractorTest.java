package use_case.search;

import data_access.FirebaseListingDataAccessObject;
import entity.listing.Listing;
import entity.book.Book;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SearchInteractorTest {

    private FirebaseListingDataAccessObject bookDataAccessObject;
    private SearchOutputBoundary searchOutputBoundary;
    private SearchInteractor searchInteractor;

    @BeforeEach
    void setUp() {
        bookDataAccessObject = mock(FirebaseListingDataAccessObject.class);
        searchOutputBoundary = mock(SearchOutputBoundary.class);
        searchInteractor = new SearchInteractor(bookDataAccessObject, searchOutputBoundary);
    }

    @Test
    void testExecute_SuccessfulSearch() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        Book book1 = mock(Book.class);
        when(listing1.getBook()).thenReturn(book1);
        when(book1.getBookId()).thenReturn("123");
        when(book1.getAuthors()).thenReturn("Shakespeare");
        when(book1.getTitle()).thenReturn("Hamlet");
        when(listing1.getPrice()).thenReturn("20");

        Listing listing2 = mock(Listing.class);
        Book book2 = mock(Book.class);
        when(listing2.getBook()).thenReturn(book2);
        when(book2.getBookId()).thenReturn("456");
        when(book2.getAuthors()).thenReturn("Dickens");
        when(book2.getTitle()).thenReturn("A Tale of Two Cities");
        when(listing2.getPrice()).thenReturn("25");

        List<Listing> allListings = List.of(listing1, listing2);

        // Mock the data access object to return the listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Prepare search input (will match listing1)
        String bookID = "123";
        String authors = "Shakespeare";
        String title = "Hamlet";
        String price = "";

        SearchInputData searchInputData = new SearchInputData("user1", bookID, authors, title, price);

        // Act: Execute the search
        searchInteractor.execute(searchInputData);

        // Assert: Verify success and that the correct output is passed to the presenter
        ArgumentCaptor<SearchOutputData> argumentCaptor = ArgumentCaptor.forClass(SearchOutputData.class);
        verify(searchOutputBoundary).prepareSuccessView(argumentCaptor.capture());

        SearchOutputData outputData = argumentCaptor.getValue();

        // Assert that the correct listing (listing1) is returned
        assertEquals("user1", outputData.getUsername());
        assertEquals(1, outputData.getListings().size());
        assertEquals("123", outputData.getListings().get(0).getBook().getBookId());
        assertEquals("Shakespeare", outputData.getListings().get(0).getBook().getAuthors());
        assertEquals("Hamlet", outputData.getListings().get(0).getBook().getTitle());
    }

    @Test
    void testExecute_FailureSearch() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        Book book1 = mock(Book.class);
        when(listing1.getBook()).thenReturn(book1);
        when(book1.getBookId()).thenReturn("123");
        when(book1.getAuthors()).thenReturn("John Doe");
        when(book1.getTitle()).thenReturn("Great Book");
        when(listing1.getPrice()).thenReturn("50");

        Listing listing2 = mock(Listing.class);
        Book book2 = mock(Book.class);
        when(listing2.getBook()).thenReturn(book2);
        when(book2.getBookId()).thenReturn("456");
        when(book2.getAuthors()).thenReturn("Jane Doe");
        when(book2.getTitle()).thenReturn("Another Book");
        when(listing2.getPrice()).thenReturn("30");

        List<Listing> allListings = List.of(listing1, listing2);

        // Setup mock to return all listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query that does not match any listing
        String bookID = "nonexistentID";
        String authors = "nonexistentAuthor";
        String title = "Nonexistent Book";
        String price = "999";

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: Verify that the failure message is passed to the presenter
        verify(searchOutputBoundary).prepareFailView("No matching results found");
    }

    @Test
    void testExecute_FailureSearch_AllNull() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        Book book1 = mock(Book.class);
        when(listing1.getBook()).thenReturn(book1);
        when(book1.getBookId()).thenReturn(null);
        when(book1.getAuthors()).thenReturn(null);
        when(book1.getTitle()).thenReturn(null);
        when(listing1.getPrice()).thenReturn(null);

        Listing listing2 = mock(Listing.class);
        Book book2 = mock(Book.class);
        when(listing2.getBook()).thenReturn(book2);
        when(book2.getBookId()).thenReturn(null);
        when(book2.getAuthors()).thenReturn(null);
        when(book2.getTitle()).thenReturn(null);
        when(listing2.getPrice()).thenReturn(null);

        List<Listing> allListings = List.of(listing1, listing2);

        // Setup mock to return all listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query that does not match any listing
        String bookID = null;
        String authors = null;
        String title = null;
        String price = null;

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        SearchOutputData searchOutputData = new SearchOutputData("username", allListings, false);
        searchInteractor.execute(searchInputData);

        // Assert: Verify that the failure message is passed to the presenter
        verify(searchOutputBoundary).prepareFailView("No matching results found");
        assertFalse(searchOutputData.isUseCaseFailed());
    }

    @Test
    void testExecute_NoMatchingResults() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        Book book1 = mock(Book.class);
        when(listing1.getBook()).thenReturn(book1);
        when(book1.getBookId()).thenReturn("123");
        when(book1.getAuthors()).thenReturn("Shakespeare");
        when(book1.getTitle()).thenReturn("Hamlet");
        when(listing1.getPrice()).thenReturn("20");

        Listing listing2 = mock(Listing.class);
        Book book2 = mock(Book.class);
        when(listing2.getBook()).thenReturn(book2);
        when(book2.getBookId()).thenReturn("456");
        when(book2.getAuthors()).thenReturn("Dickens");
        when(book2.getTitle()).thenReturn("A Tale of Two Cities");
        when(listing2.getPrice()).thenReturn("25");

        List<Listing> allListings = List.of(listing1, listing2);

        // Mock the data access object to return the listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Prepare search input (no match)
        String bookID = "999"; // Non-matching book ID
        String authors = "Hemingway"; // Non-matching author
        String title = "The Old Man and the Sea"; // Non-matching title
        String price = "30"; // Non-matching price

        SearchInputData searchInputData = new SearchInputData("user1", bookID, authors, title, price);
        SearchOutputData searchOutputData = new SearchOutputData("user1", allListings, false);

        // Act: Execute the search
        searchInteractor.execute(searchInputData);

        // Assert: Verify the failure message
        verify(searchOutputBoundary).prepareFailView(eq("No matching results found"));
        assertFalse(searchOutputData.isUseCaseFailed());
    }

    @Test
    void testExecute_PartialMatch() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        Book book1 = mock(Book.class);
        when(listing1.getBook()).thenReturn(book1);
        when(book1.getBookId()).thenReturn("123");
        when(book1.getAuthors()).thenReturn("Shakespeare");
        when(book1.getTitle()).thenReturn("Hamlet");
        when(listing1.getPrice()).thenReturn("20");

        Listing listing2 = mock(Listing.class);
        Book book2 = mock(Book.class);
        when(listing2.getBook()).thenReturn(book2);
        when(book2.getBookId()).thenReturn("456");
        when(book2.getAuthors()).thenReturn("Dickens");
        when(book2.getTitle()).thenReturn("A Tale of Two Cities");
        when(listing2.getPrice()).thenReturn("25");

        List<Listing> allListings = List.of(listing1, listing2);

        // Mock the data access object to return the listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Prepare search input (partial match on authors and price)
        String bookID = "";
        String authors = "Dickens"; // Partial match on author
        String title = "";
        String price = "25"; // Partial match on price

        SearchInputData searchInputData = new SearchInputData("user1", bookID, authors, title, price);

        // Act: Execute the search
        searchInteractor.execute(searchInputData);

        // Assert: Verify success and that the correct output is passed to the presenter
        ArgumentCaptor<SearchOutputData> argumentCaptor = ArgumentCaptor.forClass(SearchOutputData.class);
        verify(searchOutputBoundary).prepareSuccessView(argumentCaptor.capture());

        SearchOutputData outputData = argumentCaptor.getValue();

        // Assert that the correct listing (listing2) is returned
        assertEquals("user1", outputData.getUsername());
        assertEquals(1, outputData.getListings().size());
        assertEquals("456", outputData.getListings().get(0).getBook().getBookId());
        assertEquals("Dickens", outputData.getListings().get(0).getBook().getAuthors());
        assertEquals("A Tale of Two Cities", outputData.getListings().get(0).getBook().getTitle());
    }
}