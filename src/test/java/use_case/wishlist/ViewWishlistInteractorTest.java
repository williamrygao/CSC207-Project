package use_case.wishlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import entity.listing.Listing;
import entity.user.User;
import use_case.wishlist.view_wishlist.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ViewWishlistInteractorTest {
    private ViewWishlistUserDataAccessInterface userDataAccessObject;
    private ViewWishlistOutputBoundary viewWishlistPresenter;
    private ViewWishlistInteractor interactor;

    @BeforeEach
    void setUp() {
        userDataAccessObject = mock(ViewWishlistUserDataAccessInterface.class);
        viewWishlistPresenter = mock(ViewWishlistOutputBoundary.class);
        interactor = new ViewWishlistInteractor(userDataAccessObject, viewWishlistPresenter);
    }

    @Test
    void testExecute_NonexistentUser() {
        String username = "nonexistent_user";
        when(userDataAccessObject.existsByName(username)).thenReturn(false);
        ViewWishlistInputData inputData = new ViewWishlistInputData(username);

        interactor.execute(inputData);

        verify(viewWishlistPresenter, times(1))
                .prepareFailView("User does not exist.");
    }

    @Test
    void successTest_NonemptyWishlist() {
        String username = "test_user";
        User user = mock(User.class);
        List<Listing> wishlist = new ArrayList<>();
        wishlist.add(mock(Listing.class));
        wishlist.add(mock(Listing.class));

        when(userDataAccessObject.existsByName(username)).thenReturn(true);
        when(userDataAccessObject.get(username)).thenReturn(user);
        when(userDataAccessObject.getWishlist(user)).thenReturn(wishlist);

        ViewWishlistInputData inputData = new ViewWishlistInputData(username);

        interactor.execute(inputData);

        ArgumentCaptor<ViewWishlistOutputData> captor = ArgumentCaptor.forClass(ViewWishlistOutputData.class);
        verify(viewWishlistPresenter).prepareSuccessView(captor.capture());

        ViewWishlistOutputData capturedOutput = captor.getValue();
        assertEquals("test_user", capturedOutput.getUsername());
        assertEquals(wishlist, capturedOutput.getWishlist());
        assertFalse(capturedOutput.isUseCaseFailed());
    }

    @Test
    void successTest_EmptyWishlist() {
        String username = "test_user";
        User user = mock(User.class);
        List<Listing> emptyWishlist = new ArrayList<>();

        when(userDataAccessObject.existsByName(username)).thenReturn(true);
        when(userDataAccessObject.get(username)).thenReturn(user);
        when(userDataAccessObject.getWishlist(user)).thenReturn(emptyWishlist);

        ViewWishlistInputData inputData = new ViewWishlistInputData(username);

        // Act
        interactor.execute(inputData);

        ArgumentCaptor<ViewWishlistOutputData> captor = ArgumentCaptor.forClass(ViewWishlistOutputData.class);
        verify(viewWishlistPresenter).prepareSuccessView(captor.capture());

        ViewWishlistOutputData capturedOutput = captor.getValue();
        assertEquals("test_user", capturedOutput.getUsername());
        assertEquals(emptyWishlist, capturedOutput.getWishlist());
        assertFalse(capturedOutput.isUseCaseFailed());
    }
}
