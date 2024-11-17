package interface_adapter.to_sell_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;
import use_case.to_sell_view.ToSellOutputBoundary;
import use_case.to_sell_view.ToSellOutputData;

/**
 * The Presenter for the To Sell Use Case.
 */
public class ToSellPresenter implements ToSellOutputBoundary {

    /**
     * LoggedInViewModel.
     */
    private final LoggedInViewModel loggedInViewModel;
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
     * @param loggedInViewModel the view model of the logged-in user's state
     * @param sellViewModel the view model representing the sell state
     */
    public ToSellPresenter(final ViewManagerModel viewManagerModel,
                           final LoggedInViewModel loggedInViewModel,
                           final SellViewModel sellViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.sellViewModel = sellViewModel;
    }

    /**
     * Overrides prepareSuccessView method.
     * @param response the output data
     */
    @Override
    public void prepareSuccessView(final ToSellOutputData response) {
        final LoggedInState loggedInState = loggedInViewModel.getState();

        final SellState sellState = sellViewModel.getState();
        sellState.setUsername(loggedInState.getUsername());
        sellState.setPassword(loggedInState.getPassword());
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
