package use_case.filter_by_price;

import entity.book.Book;
import entity.listing.Listing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the FilterByPriceInteractor.
 */
public class FilterByPriceInteractorTest {

    private FilterByPriceDataAccessInterface mockDataAccess;
    private FilterByPriceOutputBoundary mockPresenter;
    private FilterByPriceInteractor interactor;

    @BeforeEach
    public void setUp() {
        mockDataAccess = mock(FilterByPriceDataAccessInterface.class);
        mockPresenter = mock(FilterByPriceOutputBoundary.class);
        interactor = new FilterByPriceInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    public void testExecute_NoListings_PreparesFailView() {
        // Arrange: Set up empty listings
        when(mockDataAccess.getListings()).thenReturn(new ArrayList<>());

        FilterByPriceInputData inputData = new FilterByPriceInputData(100);

        // Act
        interactor.execute(inputData);

        // Assert: Verify fail view is prepared
        verify(mockPresenter).prepareFailView("No listings yet.");
        verifyNoMoreInteractions(mockPresenter);
    }

    @Test
    public void testExecute_ListingsBelowMaxPrice_PreparesSuccessView() {
        // Arrange: Create mock listings with different prices
        Book book1 = new Book("1", "Book One", "Author One", "Genre One", "50");
        Book book2 = new Book("2", "Book Two", "Author Two", "Genre Two", "80");
        Listing listing1 = new Listing("1", book1, "50", "Seller One", true);
        Listing listing2 = new Listing("2", book2, "80", "Seller Two", true);
        Listing listing3 = new Listing("3", book1, "120", "Seller Three", true);

        List<Listing> mockListings = List.of(listing1, listing2, listing3);

        when(mockDataAccess.getListings()).thenReturn(mockListings);

        FilterByPriceInputData inputData = new FilterByPriceInputData(100);

        // Act
        interactor.execute(inputData);

        // Assert: Verify success view is prepared with correct filtered listings
        ArgumentCaptor<FilterByPriceOutputData> outputDataCaptor = ArgumentCaptor.forClass(FilterByPriceOutputData.class);
        verify(mockPresenter).prepareSuccessView(outputDataCaptor.capture());

        FilterByPriceOutputData outputData = outputDataCaptor.getValue();
        assertEquals(2, outputData.getListings().size());
        assertEquals("50", outputData.getListings().get(0).getPrice());
        assertEquals("80", outputData.getListings().get(1).getPrice());
    }

    @Test
    public void testExecute_NoListingsBelowMaxPrice_PreparesSuccessViewWithEmptyList() {
        // Arrange: Create mock listings with prices higher than max
        Book book1 = new Book("1", "Book One", "Author One", "Genre One", "150");
        Listing listing1 = new Listing("1", book1, "150", "Seller One", true);
        Listing listing2 = new Listing("2", book1, "200", "Seller Two", true);

        List<Listing> mockListings = List.of(listing1, listing2);

        when(mockDataAccess.getListings()).thenReturn(mockListings);

        FilterByPriceInputData inputData = new FilterByPriceInputData(100);

        // Act
        interactor.execute(inputData);

        // Assert: Verify success view is prepared with an empty list
        ArgumentCaptor<FilterByPriceOutputData> outputDataCaptor = ArgumentCaptor.forClass(FilterByPriceOutputData.class);
        verify(mockPresenter).prepareSuccessView(outputDataCaptor.capture());

        FilterByPriceOutputData outputData = outputDataCaptor.getValue();
        assertEquals(0, outputData.getListings().size());
    }

    @Test
    public void testExecute_MaxPriceBoundary_PreparesSuccessView() {
        // Arrange: Create mock listings with one exactly at max price
        Book book1 = new Book("1", "Book One", "Author One", "Genre One", "100");
        Listing listing1 = new Listing("1", book1, "100", "Seller One", true);
        Listing listing2 = new Listing("2", book1, "200", "Seller Two", true);

        List<Listing> mockListings = List.of(listing1, listing2);

        when(mockDataAccess.getListings()).thenReturn(mockListings);

        FilterByPriceInputData inputData = new FilterByPriceInputData(100);

        // Act
        interactor.execute(inputData);

        // Assert: Verify success view includes only the listing at the boundary
        ArgumentCaptor<FilterByPriceOutputData> outputDataCaptor = ArgumentCaptor.forClass(FilterByPriceOutputData.class);
        verify(mockPresenter).prepareSuccessView(outputDataCaptor.capture());

        FilterByPriceOutputData outputData = outputDataCaptor.getValue();
        assertEquals(1, outputData.getListings().size());
        assertEquals("100", outputData.getListings().get(0).getPrice());
    }
}
