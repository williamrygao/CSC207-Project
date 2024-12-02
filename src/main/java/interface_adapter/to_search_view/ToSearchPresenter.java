package interface_adapter.to_search_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import use_case.to_search_view.ToSearchOutputBoundary;

/**
 * The Presenter for the To Search Use Case.
 */
public class ToSearchPresenter implements ToSearchOutputBoundary {

    /**
     * LoggedInViewModel.
     */
    private final HomeViewModel homeViewModel;
    /**
     * ViewManagerModel.
     */
    private final ViewManagerModel viewManagerModel;
    /**
     * SearchViewModel.
     */
    private final SearchViewModel searchViewModel;

    /**
     * Constructs a ToSearchPresenter with the specified view models.
     * @param viewManagerModel the model that manages the current view state
     * @param homeViewModel the view model of the logged-in user's state
     * @param searchViewModel the view model representing the search state
     */
    public ToSearchPresenter(final ViewManagerModel viewManagerModel,
                           final HomeViewModel homeViewModel,
                           final SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.searchViewModel = searchViewModel;
    }

    /**
     * Overrides prepareSuccessView method.
     */
    @Override
    public void prepareSuccessView() {
        final HomeState homeState = homeViewModel.getState();

        final SearchState searchState = searchViewModel.getState();
        searchState.setUsername(homeState.getUsername());
        searchState.setPassword(homeState.getPassword());
        searchViewModel.setState(searchState);
        searchViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the SearchView.
        this.viewManagerModel.setState(searchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(final String error) {
    }
}
