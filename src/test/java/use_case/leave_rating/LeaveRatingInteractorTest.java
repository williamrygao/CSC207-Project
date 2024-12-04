package use_case.leave_rating;

import entity.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LeaveRatingInteractorTest {
    private LeaveRatingDataAccessInterface ratingDataAccess;
    private LeaveRatingOutputBoundary leaveRatingPresenter;
    private LeaveRatingInteractor interactor;

    @BeforeEach
    void setUp() {
        ratingDataAccess = mock(LeaveRatingDataAccessInterface.class);
        leaveRatingPresenter = mock(LeaveRatingOutputBoundary.class);
        interactor = new LeaveRatingInteractor(ratingDataAccess, leaveRatingPresenter);
    }

    @Test
    void testExecute_BookNotFound() {
        String bookId = "nonexistent_book_id";
        String username = "test_user";
        String password = "test_password";
        Integer newRating = 5;

        LeaveRatingInputData inputData = new LeaveRatingInputData(username,password, bookId, newRating);

        when(ratingDataAccess.existsByBookID(bookId)).thenReturn(false);

        interactor.execute(inputData);

        verify(leaveRatingPresenter).prepareFailView("Book not found for ID: " + bookId);
    }

    @Test
    void testExecute_InvalidRating() {
        String bookId = "valid_book_id";
        String username = "test_user";
        String password = "test_password";
        Integer invalidRating = 11; // Invalid rating (out of range)

        LeaveRatingInputData inputData = new LeaveRatingInputData(username, password, bookId, invalidRating);

        when(ratingDataAccess.existsByBookID(bookId)).thenReturn(true);
        Rating existingRating = new Rating(bookId);
        when(ratingDataAccess.getRatingsByBookID(bookId)).thenReturn(existingRating);

        interactor.execute(inputData);

        verify(leaveRatingPresenter).prepareFailView("Invalid rating: " + invalidRating + ". Rating must be between 1 and 10.");
    }

    @Test
    void testExecute_ValidRating() {
        String bookId = "valid_book_id";
        String username = "test_user";
        String password = "test_password";
        Integer newRating = 8;

        LeaveRatingInputData inputData = new LeaveRatingInputData(username, password, bookId, newRating);

        when(ratingDataAccess.existsByBookID(bookId)).thenReturn(true);
        Rating existingRating = new Rating(bookId);
        when(ratingDataAccess.getRatingsByBookID(bookId)).thenReturn(existingRating);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<LeaveRatingOutputData> captor = ArgumentCaptor.forClass(LeaveRatingOutputData.class);
        verify(ratingDataAccess).save(existingRating);
        verify(leaveRatingPresenter).prepareSuccessView(captor.capture());

        LeaveRatingOutputData output = captor.getValue();
        assertEquals(username, output.getUsername());
        assertFalse(output.isUseCaseFailed());
    }

    @Test
    void testExecute_ExceptionOnSave() {
        String bookId = "valid_book_id";
        String username = "test_user";
        String password = "test_password";
        Integer newRating = 5;

        LeaveRatingInputData inputData = new LeaveRatingInputData(username,password , bookId, newRating);

        when(ratingDataAccess.existsByBookID(bookId)).thenReturn(true);
        Rating existingRating = new Rating(bookId);
        when(ratingDataAccess.getRatingsByBookID(bookId)).thenReturn(existingRating);

        doThrow(new RuntimeException("Unexpected error")).when(ratingDataAccess).save(existingRating);

        interactor.execute(inputData);

        verify(leaveRatingPresenter).prepareFailView("Failed to leave rating due to unexpected error.");
    }

    @Test
    void testExecute_NoRatingFound() {
        String bookId = "valid_book_id";
        String username = "test_user";
        String password = "test_password";
        Integer newRating = 7;

        LeaveRatingInputData inputData = new LeaveRatingInputData(username,password , bookId, newRating);

        when(ratingDataAccess.existsByBookID(bookId)).thenReturn(true);
        when(ratingDataAccess.getRatingsByBookID(bookId)).thenReturn(null); // No existing rating

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<LeaveRatingOutputData> captor = ArgumentCaptor.forClass(LeaveRatingOutputData.class);
        verify(ratingDataAccess).save(any(Rating.class)); // Ensure the new rating was saved
        verify(leaveRatingPresenter).prepareSuccessView(captor.capture());

        LeaveRatingOutputData output = captor.getValue();
        assertEquals(username, output.getUsername());
        assertFalse(output.isUseCaseFailed());
    }
}
