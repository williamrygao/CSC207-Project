package interface_adapter.to_sell;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;
import use_case.to_sell_view.ToSellOutputBoundary;

/**
 * The Presenter for the To Sell Use Case.
 */
public class ToSellPresenter implements ToSellOutputBoundary {

    /**
     * LoggedInViewModel.
     */
    private final HomeViewModel homeViewModel;
    /**
     * ViewManagerModel.
     */
    private final ViewManagerModel viewManagerModel;
    /**
     * SellViewModel.
     */
    private final SellViewModel sellViewModel;

    /**
     * Constructs a ToSellPresenter with the specified view models.
     * @param viewManagerModel the model that manages the current view state
     * @param homeViewModel the view model of the logged-in user's state
     * @param sellViewModel the view model representing the sell state
     */
    public ToSellPresenter(final ViewManagerModel viewManagerModel,
                           final HomeViewModel homeViewModel,
                           final SellViewModel sellViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.sellViewModel = sellViewModel;
    }

    /**
     * Overrides prepareSuccessView method.
     */
    @Override
    public void prepareSuccessView() {
        final HomeState homeState = homeViewModel.getState();

        final SellState sellState = sellViewModel.getState();
        sellState.setUsername(homeState.getUsername());
        sellState.setPassword(homeState.getPassword());
        sellViewModel.setState(sellState);
        sellViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the SellView.
        this.viewManagerModel.setState(sellViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(final String error) {
    }
}
