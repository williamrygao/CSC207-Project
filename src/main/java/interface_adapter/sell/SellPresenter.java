package interface_adapter.sell;

import use_case.sell.SellOutputBoundary;
import use_case.sell.SellOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class SellPresenter implements SellOutputBoundary {

    private final SellViewModel sellViewModel;

    public SellPresenter(SellViewModel sellViewModel) {
        this.sellViewModel = sellViewModel;
    }

    @Override
    public void prepareSuccessView(SellOutputData outputData) {
        // currently there isn't anything to change based on the output data,
        // since the output data only contains the username, which remains the same.
        // We still fire the property changed event, but just to let the view know that
        // it can alert the user that their password was changed successfully.
        sellViewModel.firePropertyChanged("password");
    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }
}
