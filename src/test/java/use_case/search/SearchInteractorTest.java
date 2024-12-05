package use_case.search;

import data_access.FirebaseListingDataAccessObject;
import entity.listing.Listing;
import entity.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
        when(listing1.getBook()).thenReturn(mock(Book.class));
        when(listing1.getBook().getTitle()).thenReturn("Great Book");
        when(listing1.getBook().getAuthors()).thenReturn("John Doe");
        when(listing1.getPrice()).thenReturn("50");

        Listing listing2 = mock(Listing.class);
        when(listing2.getBook()).thenReturn(mock(Book.class));
        when(listing2.getBook().getTitle()).thenReturn("Another Book");
        when(listing2.getBook().getAuthors()).thenReturn("Jane Doe");
        when(listing2.getPrice()).thenReturn("30");

        List<Listing> allListings = List.of(listing1, listing2);

        // Setup mock to return all listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query that matches listing1
        String bookID = "";
        String authors = "John Doe";
        String title = "Great Book";
        String price = "50";

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: Only the correct listing should be returned (listing1)
        verify(searchOutputBoundary).prepareSuccessView(any());
    }

    @Test
    void testExecute_NoResultsFound() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        Listing listing2 = mock(Listing.class);
        List<Listing> allListings = List.of(listing1, listing2);

        // Setup mock to return all listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query that does not match any listings
        String bookID = "nonexistentID";
        String authors = "unknownAuthor";
        String title = "nonexistentTitle";
        String price = "100";

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: No listings should match the criteria
        verify(searchOutputBoundary).prepareFailView("No matching results found");
    }

    @Test
    void testExecute_EmptySearchFields() {
        // Arrange: Setup mock data
        Listing listing1 = mock(Listing.class);
        Listing listing2 = mock(Listing.class);
        List<Listing> allListings = List.of(listing1, listing2);

        // Setup mock to return all listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query (all fields are empty)
        String bookID = "";
        String authors = "";
        String title = "";
        String price = "";

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: All listings should be returned (since no filters are applied)
        verify(searchOutputBoundary).prepareSuccessView(any());
    }

    @Test
    void testExecute_OnlyBookTitleMatches() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        Listing listing2 = mock(Listing.class);
        List<Listing> allListings = List.of(listing1, listing2);

        // Setup mock to return all listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query (only title is provided)
        String bookID = "";
        String authors = "";
        String title = "Great Book";
        String price = "";

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: Verify only listings that match the title are included
        verify(searchOutputBoundary).prepareSuccessView(any());
    }

    @Test
    void testExecute_OnlyBookAuthorsMatches() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        Listing listing2 = mock(Listing.class);
        List<Listing> allListings = List.of(listing1, listing2);

        // Setup mock to return all listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query (only authors is provided)
        String bookID = "";
        String authors = "John Doe";
        String title = "";
        String price = "";

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: Verify only listings that match the authors are included
        verify(searchOutputBoundary).prepareSuccessView(any());
    }

    @Test
    void testExecute_OnlyBookPriceMatches() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        Listing listing2 = mock(Listing.class);
        List<Listing> allListings = List.of(listing1, listing2);

        // Setup mock to return all listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query (only price is provided)
        String bookID = "";
        String authors = "";
        String title = "";
        String price = "50";

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: Verify only listings that match the price are included
        verify(searchOutputBoundary).prepareSuccessView(any());
    }

    @Test
    void testExecute_CaseInsensitiveSearch() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        when(listing1.getBook()).thenReturn(mock(Book.class));
        when(listing1.getBook().getTitle()).thenReturn("Great Book");
        when(listing1.getBook().getAuthors()).thenReturn("John Doe");
        when(listing1.getPrice()).thenReturn("50");

        List<Listing> allListings = List.of(listing1);

        // Setup mock to return all listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query with different letter casing
        String bookID = "";
        String authors = "john doe";
        String title = "great book";
        String price = "50";

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: The search should match, verifying case insensitivity
        verify(searchOutputBoundary).prepareSuccessView(any());
    }

    @Test
    void testExecute_MultipleFieldsMatches() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        when(listing1.getBook()).thenReturn(mock(Book.class));
        when(listing1.getBook().getTitle()).thenReturn("Great Book");
        when(listing1.getBook().getAuthors()).thenReturn("John Doe");
        when(listing1.getPrice()).thenReturn("50");

        Listing listing2 = mock(Listing.class);
        when(listing2.getBook()).thenReturn(mock(Book.class));
        when(listing2.getBook().getTitle()).thenReturn("Great Book");
        when(listing2.getBook().getAuthors()).thenReturn("Jane Doe");
        when(listing2.getPrice()).thenReturn("30");

        List<Listing> allListings = List.of(listing1, listing2);

        // Setup mock to return all listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query that matches multiple fields
        String bookID = "";
        String authors = "John Doe";
        String title = "Great Book";
        String price = "50";

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: Verify only the listing that matches all criteria is returned
        verify(searchOutputBoundary).prepareSuccessView(any());
    }

    @Test
    void testExecute_ExactMatchSearch() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        when(listing1.getBook()).thenReturn(mock(Book.class));
        when(listing1.getBook().getTitle()).thenReturn("Perfect Match");
        when(listing1.getBook().getAuthors()).thenReturn("John Smith");
        when(listing1.getPrice()).thenReturn("25");

        List<Listing> allListings = List.of(listing1);

        // Setup mock to return all listings
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query that matches exactly
        String bookID = "";
        String authors = "John Smith";
        String title = "Perfect Match";
        String price = "25";

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: Verify the exact match is returned
        verify(searchOutputBoundary).prepareSuccessView(any());
    }

    @Test
    void testExecute_MatchOnlyBookID() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        when(listing1.getBook()).thenReturn(mock(Book.class));
        when(listing1.getBook().getTitle()).thenReturn("Another Book");
        when(listing1.getBook().getAuthors()).thenReturn("John Doe");
        when(listing1.getPrice()).thenReturn("50");

        Listing listing2 = mock(Listing.class);
        when(listing2.getBook()).thenReturn(mock(Book.class));
        when(listing2.getBook().getTitle()).thenReturn("Book Not Matching");
        when(listing2.getBook().getAuthors()).thenReturn("Jane Doe");
        when(listing2.getPrice()).thenReturn("30");

        List<Listing> allListings = List.of(listing1, listing2);
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query with only bookID matching
        String bookID = "123"; // Matches bookID for listing1
        String authors = "";
        String title = "";
        String price = "";

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: Verify only the listing that matches the bookID is returned
        verify(searchOutputBoundary).prepareSuccessView(any());
    }

    @Test
    void testExecute_MatchOnlyPrice() {
        // Arrange: Set up mock data
        Listing listing1 = mock(Listing.class);
        when(listing1.getBook()).thenReturn(mock(Book.class));
        when(listing1.getBook().getTitle()).thenReturn("Some Book");
        when(listing1.getBook().getAuthors()).thenReturn("John Doe");
        when(listing1.getPrice()).thenReturn("50");

        Listing listing2 = mock(Listing.class);
        when(listing2.getBook()).thenReturn(mock(Book.class));
        when(listing2.getBook().getTitle()).thenReturn("Another Book");
        when(listing2.getBook().getAuthors()).thenReturn("Jane Doe");
        when(listing2.getPrice()).thenReturn("30");

        List<Listing> allListings = List.of(listing1, listing2);
        when(bookDataAccessObject.getListings()).thenReturn(allListings);

        // Set up search query with only price matching
        String bookID = "";
        String authors = "";
        String title = "";
        String price = "50"; // Matches price for listing1

        // Act: Execute the search
        SearchInputData searchInputData = new SearchInputData("username", bookID, authors, title, price);
        searchInteractor.execute(searchInputData);

        // Assert: Verify only the listing that matches the price is returned
        verify(searchOutputBoundary).prepareSuccessView(any());
    }
}