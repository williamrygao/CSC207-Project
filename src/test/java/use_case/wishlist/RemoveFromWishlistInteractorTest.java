package use_case.wishlist;

import entity.listing.Listing;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.wishlist.remove_from_wishlist.*;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RemoveFromWishlistInteractorTest {

    private RemoveFromWishlistUserDataAccessInterface userDataAccessObject;
    private RemoveFromWishlistOutputBoundary removeFromWishlistPresenter;
    private RemoveFromWishlistInteractor interactor;

    @BeforeEach
    void setUp() {
        userDataAccessObject = mock(RemoveFromWishlistUserDataAccessInterface.class);
        removeFromWishlistPresenter = mock(RemoveFromWishlistOutputBoundary.class);
        interactor = new RemoveFromWishlistInteractor(userDataAccessObject, removeFromWishlistPresenter);
    }

    @Test
    void testUserDoesNotExist() {
        // Arrange
        String username = "nonexistent_user";
        Listing listing = mock(Listing.class);
        RemoveFromWishlistInputData inputData = new RemoveFromWishlistInputData(username, listing);

        when(userDataAccessObject.existsByName(username)).thenReturn(false);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(removeFromWishlistPresenter).prepareFailView("User does not exist.");
    }

    @Test
    void testSuccessfulRemoveFromWishlist() {
        // Arrange
        String username = "test_user";
        User user = mock(User.class);
        Listing listing = mock(Listing.class);
        RemoveFromWishlistInputData inputData = new RemoveFromWishlistInputData(username, listing);

        when(userDataAccessObject.existsByName(username)).thenReturn(true);
        when(userDataAccessObject.get(username)).thenReturn(user);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<RemoveFromWishlistOutputData> captor = ArgumentCaptor.forClass(RemoveFromWishlistOutputData.class);

        verify(userDataAccessObject).removeFromWishlist(user, listing);
        verify(removeFromWishlistPresenter).prepareSuccessView(captor.capture());

        RemoveFromWishlistOutputData capturedOutput = captor.getValue();
        assertEquals(username, capturedOutput.getUsername());
        assertFalse(capturedOutput.isUseCaseFailed());
    }
}
