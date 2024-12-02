package interface_adapter.to_filter_by_rating;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.filter_by_rating.FilterByRatingState;
import interface_adapter.filter_by_rating.FilterByRatingViewModel;
import use_case.to_filter_by_rating.ToFilterByRatingOutputBoundary;

/**
 * The Presenter for the To Filter By Rating Use Case.
 */
public class ToFilterByRatingPresenter implements ToFilterByRatingOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;
    private final FilterByRatingViewModel filterByRatingViewModel;

    /**
     * Constructs a ToFilterByRatingPresenter with the specified view models.
     * @param viewManagerModel the model that manages the current view state
     * @param homeViewModel the view model of the logged-in user's state
     * @param filterByRatingViewModel the view model representing the search state
     */
    public ToFilterByRatingPresenter(final ViewManagerModel viewManagerModel,
                                     final HomeViewModel homeViewModel,
                                     final FilterByRatingViewModel filterByRatingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.filterByRatingViewModel = filterByRatingViewModel;
    }

    /**
     * Overrides prepareSuccessView method.
     */
    @Override
    public void prepareSuccessView() {
        // get state information
        final HomeState homeState = homeViewModel.getState();
        final FilterByRatingState filterByRatingState = filterByRatingViewModel.getState();

        // update Filter By Rating state information
        filterByRatingState.setUsername(homeState.getUsername());
        filterByRatingState.setPassword(homeState.getPassword());

        // update view model with new state
        filterByRatingViewModel.setState(filterByRatingState);
        filterByRatingViewModel.firePropertyChanged();

        // tells the view manager to switch to the FilterByRatingView.
        this.viewManagerModel.setState(filterByRatingViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
