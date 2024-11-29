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

    // Update the state with a new bookID
    public void setBookID(String bookID) {
        LeaveRatingState currentState = getState();
        currentState.setBookID(bookID);
        setState(currentState);
    }

    // Update the state with a new rating
    public void setRating(double rating) {
        LeaveRatingState currentState = getState();
        currentState.setNewRating(rating);
        setState(currentState);
    }

    // Update the state with an error message
    public void setErrorMessage(String errorMessage) {
        LeaveRatingState currentState = getState();
        currentState.setError(errorMessage);
        setState(currentState);
    }

    // Update the state with a success message
    public void setSuccessMessage(String successMessage) {
        LeaveRatingState currentState = getState();
        currentState.setSuccessMessage(successMessage);
        setState(currentState);
    }
}
