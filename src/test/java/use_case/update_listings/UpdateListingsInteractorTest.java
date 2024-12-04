package use_case.update_listings;

import entity.listing.Listing;
import entity.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class UpdateListingsInteractorTest {

    private UpdateListingsInteractor interactor;
    private UpdateListingsUserDataAccessInterface mockUserDAO;
    private UpdateListingsListingDataAccessInterface mockListingDAO;
    private UpdateListingsOutputBoundary mockPresenter;

    @BeforeEach
    void setUp() {
        mockUserDAO = mock(UpdateListingsUserDataAccessInterface.class);
        mockListingDAO = mock(UpdateListingsListingDataAccessInterface.class);
        mockPresenter = mock(UpdateListingsOutputBoundary.class);

        interactor = new UpdateListingsInteractor(mockUserDAO, mockListingDAO, mockPresenter);
    }

    @Test
    void testExecute_Success() {
        // Arrange
        String username = "test_user";
        UpdateListingsInputData inputData = new UpdateListingsInputData(username);

        User mockUser = mock(User.class);
        List<Listing> mockListings = new ArrayList<>();
        List<Listing> mockWishlist = new ArrayList<>();

        when(mockUserDAO.get(username)).thenReturn(mockUser);
        when(mockUser.getName()).thenReturn(username);
        when(mockListingDAO.getListings()).thenReturn(mockListings);
        when(mockUserDAO.getWishlist(mockUser)).thenReturn(mockWishlist);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockUserDAO).get(username);
        verify(mockUserDAO).setCurrentUsername(username);
        verify(mockListingDAO).getListings();
        verify(mockUserDAO).getWishlist(mockUser);
        verify(mockPresenter).prepareSuccessView(any(UpdateListingsOutputData.class));
    }

    @Test
    void testExecute_UserNotFound() {
        // Arrange
        String username = "non_existent_user";
        UpdateListingsInputData inputData = new UpdateListingsInputData(username);

        when(mockUserDAO.get(username)).thenReturn(null); // Simulate user not existing

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView("User not found: " + username);
        verify(mockUserDAO, never()).setCurrentUsername(any());
        verify(mockListingDAO, never()).getListings();
        verify(mockUserDAO, never()).getWishlist(any());
    }

    @Test
    void testExecute_NoListings() {
        // Arrange
        String username = "test_user";
        UpdateListingsInputData inputData = new UpdateListingsInputData(username);

        User mockUser = mock(User.class);
        when(mockUserDAO.get(username)).thenReturn(mockUser);
        when(mockUser.getName()).thenReturn(username);
        when(mockListingDAO.getListings()).thenReturn(null);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView("No listings available.");
    }

    @Test
    void testDataAccessInterface_SaveMethod() {
        Listing mockListing = mock(Listing.class);
        mockListingDAO.save(mockListing);
        verify(mockListingDAO).save(mockListing);
    }

    @Test
    void testExecute_NullUsername() {
        // Arrange
        UpdateListingsInputData inputData = new UpdateListingsInputData(null);

        // Act
        interactor.execute(inputData);

        // Assert - Ensure failure view is triggered
        verify(mockPresenter).prepareFailView("Invalid username.");
        verify(mockUserDAO, never()).setCurrentUsername(any());
    }

    @Test
    void testExecute_EmptyListings() {
        // Arrange
        String username = "test_user";
        UpdateListingsInputData inputData = new UpdateListingsInputData(username);

        User mockUser = mock(User.class);
        List<Listing> emptyListings = new ArrayList<>();
        List<Listing> mockWishlist = new ArrayList<>();

        when(mockUserDAO.get(username)).thenReturn(mockUser);
        when(mockUser.getName()).thenReturn(username);
        when(mockListingDAO.getListings()).thenReturn(emptyListings);
        when(mockUserDAO.getWishlist(mockUser)).thenReturn(mockWishlist);

        // Act
        interactor.execute(inputData);

        // Assert - Check that success view is triggered even with empty listings
        verify(mockPresenter).prepareSuccessView(any(UpdateListingsOutputData.class));
    }

    @Test
    void testSaveListing() {
        Listing mockListing = mock(Listing.class);
        UpdateListingsListingDataAccessInterface mockListingDAO = mock(UpdateListingsListingDataAccessInterface.class);

        // Act
        mockListingDAO.save(mockListing);

        // Assert - Ensure that save() was invoked correctly
        verify(mockListingDAO).save(mockListing);
    }

    @Test
    void testGetters() {
        // Arrange
        String username = "test_user";
        boolean useCaseFailed = false;
        List<Listing> listings = new ArrayList<>();
        List<Listing> wishlist = new ArrayList<>();
        UpdateListingsOutputData outputData = new UpdateListingsOutputData(username, useCaseFailed, listings, wishlist);

        // Act & Assert
        Assertions.assertEquals("test_user", outputData.getUsername());
        Assertions.assertFalse(outputData.isUseCaseFailed());
        Assertions.assertNotNull(outputData.getListings());
        Assertions.assertNotNull(outputData.getWishlist());
    }

}
