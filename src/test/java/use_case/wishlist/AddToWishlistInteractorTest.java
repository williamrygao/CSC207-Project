package use_case.wishlist;

import entity.listing.Listing;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.wishlist.add_to_wishlist.*;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AddToWishlistInteractorTest {
    private AddToWishlistUserDataAccessInterface userDataAccessObject;
    private AddToWishlistOutputBoundary addToWishlistPresenter;
    private AddToWishlistInteractor interactor;

    @BeforeEach
    void setUp() {
        userDataAccessObject = mock(AddToWishlistUserDataAccessInterface.class);
        addToWishlistPresenter = mock(AddToWishlistOutputBoundary.class);
        interactor = new AddToWishlistInteractor(userDataAccessObject, addToWishlistPresenter);
    }

    @Test
    void testExecute_NonexistentUser() {
        String username = "nonexistent_user";

        when(userDataAccessObject.existsByName(username)).thenReturn(false);
        Listing listing = mock(Listing.class);

        AddToWishlistInputData inputData = new AddToWishlistInputData(username, listing);

        interactor.execute(inputData);

        verify(addToWishlistPresenter, times(1))
                .prepareFailView("User does not exist.");
    }

    @Test
    void testSuccessfulAddToWishlist() {
        // Arrange
        String username = "test_user";
        User user = mock(User.class);
        Listing listing = mock(Listing.class);

        AddToWishlistInputData inputData = new AddToWishlistInputData(username, listing);

        when(userDataAccessObject.existsByName(username)).thenReturn(true);
        when(userDataAccessObject.get(username)).thenReturn(user);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<AddToWishlistOutputData> captor = ArgumentCaptor.forClass(AddToWishlistOutputData.class);

        verify(userDataAccessObject).addToWishlist(user, listing);
        verify(addToWishlistPresenter).prepareSuccessView(captor.capture());

        AddToWishlistOutputData capturedOutput = captor.getValue();
        assertEquals(username, capturedOutput.getUsername());
        assertFalse(capturedOutput.isUseCaseFailed());
    }
}
