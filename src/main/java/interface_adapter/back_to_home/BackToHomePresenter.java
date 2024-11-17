package interface_adapter.back_to_home;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.sell.SellViewModel;
import use_case.back_to_home.BackToHomeOutputBoundary;

/**
 * The Presenter for the BackToHome Use Case.
 */
public class BackToHomePresenter implements BackToHomeOutputBoundary {

    /**
     * ViewManagerModel.
     */
    private final ViewManagerModel viewManagerModel;
    /**
     * HomeViewModel.
     */
    private final HomeViewModel homeViewModel;
    /**
     * SellViewModel.
     */
    private final SellViewModel sellViewModel;

    public BackToHomePresenter(final ViewManagerModel viewManagerModel, final HomeViewModel homeViewModel, final SellViewModel sellViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.sellViewModel = sellViewModel;
    }

    @Override
    public void prepareSuccessView() {
        this.viewManagerModel.setState(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(final String error) {
    }
}
