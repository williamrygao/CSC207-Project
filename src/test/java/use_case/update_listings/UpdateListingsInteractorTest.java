package use_case.update_listings;

import entity.listing.Listing;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

class UpdateListingsInteractorTest {

    private UpdateListingsUserDataAccessInterface mockUserDAO;
    private UpdateListingsListingDataAccessInterface mockListingDAO;
    private UpdateListingsOutputBoundary mockPresenter;
    private UpdateListingsInteractor interactor;

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
    void testExecute_Fail_UsernameNull() {
        // Arrange
        UpdateListingsInputData inputData = new UpdateListingsInputData(null);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView("Username not found.");
        verify(mockUserDAO, never()).setCurrentUsername(any());
        verify(mockListingDAO, never()).getListings();
        verify(mockUserDAO, never()).getWishlist(any());
    }

    @Test
    void testExecute_Fail_UserNotFound() {
        // Arrange
        String username = "non_existent_user";
        UpdateListingsInputData inputData = new UpdateListingsInputData(username);

        when(mockUserDAO.get(username)).thenReturn(null);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView("User not found.");
        verify(mockUserDAO, never()).setCurrentUsername(any());
        verify(mockListingDAO, never()).getListings();
        verify(mockUserDAO, never()).getWishlist(any());
    }

    @Test
    void testUpdateListingsOutputData_Getters() {
        List<Listing> emptyListings = new ArrayList<>();
        List<Listing> emptyWishlist = new ArrayList<>();

        UpdateListingsOutputData outputData = new UpdateListingsOutputData("test_user", false, emptyListings, emptyWishlist);

        // Act & Assert
        assertEquals("test_user", outputData.getUsername());
        assertFalse(outputData.isUseCaseFailed());
        assertNotNull(outputData.getListings());
        assertNotNull(outputData.getWishlist());
    }
}
