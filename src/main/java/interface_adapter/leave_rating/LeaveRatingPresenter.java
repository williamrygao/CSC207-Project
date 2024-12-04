package interface_adapter.leave_rating;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import use_case.leave_rating.LeaveRatingOutputBoundary;
import use_case.leave_rating.LeaveRatingOutputData;

/**
 * The Presenter for the Leave Rating Use Case.
 */
public class LeaveRatingPresenter implements LeaveRatingOutputBoundary {

    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a LeaveRatingPresenter.
     *
     * @param viewManagerModel The view manager for controlling state transitions.
     * @param homeViewModel    The view model for the home screen.
     */
    public LeaveRatingPresenter(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    /**
     * Prepares the success view after successfully leaving a rating.
     *
     * @param response The output data from the use case.
     */
    @Override
    public void prepareSuccessView(LeaveRatingOutputData response) {
        // Update the HomeViewModel to reflect the change
        final HomeState homeState = homeViewModel.getState();
        homeViewModel.setState(homeState);

        // Notify view manager to refresh the home view
        homeViewModel.firePropertyChanged();
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view in case of an error while leaving a rating.
     *
     * @param error The error message to display.
     */
    @Override
    public void prepareFailView(String error) {
        // Update the HomeViewModel with the error message
        final HomeState homeState = homeViewModel.getState();
        homeViewModel.setState(homeState);

        // Notify the HomeViewModel of the change
        homeViewModel.firePropertyChanged();
    }
}
