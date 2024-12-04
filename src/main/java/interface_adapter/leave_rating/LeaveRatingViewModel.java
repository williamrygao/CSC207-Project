package interface_adapter.leave_rating;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Leave Rating feature.
 */
public class LeaveRatingViewModel extends ViewModel<LeaveRatingState> {

    public LeaveRatingViewModel() {
        super("leaveRating");
        setState(new LeaveRatingState());
    }

    /**
     * Method to update the state with a new book ID.
     * @param bookID the book ID
     */
    public void setBookID(String bookID) {
        final LeaveRatingState currentState = getState();
        currentState.setBookID(bookID);
        setState(currentState);
    }

    /**
     * Method to update the state with a new rating.
     * @param rating the book rating
     */
    public void setRating(double rating) {
        final LeaveRatingState currentState = getState();
        currentState.setNewRating(rating);
        setState(currentState);
    }

    /**
     * Method to update the state with an error message.
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        final LeaveRatingState currentState = getState();
        currentState.setError(errorMessage);
        setState(currentState);
    }

    /**
     * Method to update the state with a success message.
     * @param successMessage the success message
     */
    public void setSuccessMessage(String successMessage) {
        final LeaveRatingState currentState = getState();
        currentState.setSuccessMessage(successMessage);
        setState(currentState);
    }
}
