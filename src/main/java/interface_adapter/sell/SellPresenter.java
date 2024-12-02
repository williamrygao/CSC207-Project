package interface_adapter.sell;

import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import use_case.sell.SellOutputBoundary;
import use_case.sell.SellOutputData;

/**
 * The Presenter for the Sell Use Case.
 */
public class SellPresenter implements SellOutputBoundary {

    private final SellViewModel sellViewModel;
    private final HomeViewModel homeViewModel;

    public SellPresenter(SellViewModel sellViewModel, HomeViewModel homeViewModel) {
        this.sellViewModel = sellViewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(SellOutputData outputData) {
        // Add Listing to Home View
        final HomeState homeState = homeViewModel.getState();
        homeState.addListing(outputData.getListing());
        this.homeViewModel.setState(homeState);

        // Pop-up in Sell View.
        sellViewModel.firePropertyChanged("listed for sale");
    }

    @Override
    public void prepareFailView(String error) {
        final SellState sellState = sellViewModel.getState();
        sellState.setSellError(error);
        sellViewModel.firePropertyChanged("not sold");
    }
}
