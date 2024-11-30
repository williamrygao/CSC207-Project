package interface_adapter.back_to_home;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.HomeViewModel;
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

    public BackToHomePresenter(final ViewManagerModel viewManagerModel, final HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView() {
        this.viewManagerModel.setState(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
        homeViewModel.firePropertyChanged();
        homeViewModel.firePropertyChanged("listings");
    }

    @Override
    public void prepareFailView(final String error) {
    }
}
