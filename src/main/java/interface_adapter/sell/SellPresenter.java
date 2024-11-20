package interface_adapter.sell;

import interface_adapter.change_password.HomeViewModel;
import use_case.sell.SellOutputBoundary;
import use_case.sell.SellOutputData;

/**
 * The Presenter for the Change Password Use Case.
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
        // currently there isn't anything to change based on the output data,
        // since the output data only contains the username, which remains the same.
        // We still fire the property changed event, but just to let the view know that
        // it can alert the user that their password was changed successfully.
        sellViewModel.firePropertyChanged("listed for sale");
        homeViewModel.firePropertyChanged("listing");
    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }
}
