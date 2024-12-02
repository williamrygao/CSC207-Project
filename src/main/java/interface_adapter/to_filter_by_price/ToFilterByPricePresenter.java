package interface_adapter.to_filter_by_price;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.filter_by_price.FilterByPriceState;
import interface_adapter.filter_by_price.FilterByPriceViewModel;
import use_case.to_filter_by_price.ToFilterByPriceOutputBoundary;

/**
 * The Presenter for the To Filter By Price Use Case.
 */
public class ToFilterByPricePresenter implements ToFilterByPriceOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;
    private final FilterByPriceViewModel filterByPriceViewModel;

    /**
     * Constructs a ToFilterByPricePresenter with the specified view models.
     * @param viewManagerModel the model that manages the current view state
     * @param homeViewModel the view model of the logged-in user's state
     * @param filterByPriceViewModel the view model representing the search state
     */
    public ToFilterByPricePresenter(final ViewManagerModel viewManagerModel,
                                    final HomeViewModel homeViewModel,
                                    final FilterByPriceViewModel filterByPriceViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.filterByPriceViewModel = filterByPriceViewModel;
    }

    /**
     * Overrides prepareSuccessView method.
     */
    @Override
    public void prepareSuccessView() {
        // get state information
        final HomeState homeState = homeViewModel.getState();
        final FilterByPriceState filterByPriceState = filterByPriceViewModel.getState();

        // update Filter By Price state information
        filterByPriceState.setUsername(homeState.getUsername());
        filterByPriceState.setPassword(homeState.getPassword());

        // update view model with new state
        filterByPriceViewModel.setState(filterByPriceState);
        filterByPriceViewModel.firePropertyChanged();

        // tells the view manager to switch to the FilterByRatingView.
        this.viewManagerModel.setState(filterByPriceViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
