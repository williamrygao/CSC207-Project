package interface_adapter.to_filter_by_genre;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.filter_by_genre.FilterByGenreState;
import interface_adapter.filter_by_genre.FilterByGenreViewModel;
import use_case.to_filter_by_genre.ToFilterByGenreOutputBoundary;

/**
 * The Presenter for the To Filter By Genre Use Case.
 */
public class ToFilterByGenrePresenter implements ToFilterByGenreOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;
    private final FilterByGenreViewModel filterByGenreViewModel;

    /**
     * Constructs a ToFilterByPricePresenter with the specified view models.
     * @param viewManagerModel the model that manages the current view state
     * @param homeViewModel the view model of the logged-in user's state
     * @param filterByGenreViewModel the view model representing the search state
     */
    public ToFilterByGenrePresenter(final ViewManagerModel viewManagerModel,
                                    final HomeViewModel homeViewModel,
                                    final FilterByGenreViewModel filterByGenreViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.filterByGenreViewModel = filterByGenreViewModel;
    }

    /**
     * Overrides prepareSuccessView method.
     */
    @Override
    public void prepareSuccessView() {
        // get state information
        final HomeState homeState = homeViewModel.getState();
        final FilterByGenreState filterByGenreState = filterByGenreViewModel.getState();
        // update Filter By Genre state information
        filterByGenreState.setUsername(homeState.getUsername());
        filterByGenreState.setPassword(homeState.getPassword());
        // update view model with new state
        filterByGenreViewModel.setState(filterByGenreState);
        filterByGenreViewModel.firePropertyChanged();
        // tells the view manager to switch to the FilterByRatingView.
        this.viewManagerModel.setState(filterByGenreViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
