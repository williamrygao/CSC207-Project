package interface_adapter.back_to_signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;
import use_case.back_to_signup.BackToSignupOutputBoundary;

/**
 * The Presenter for the BackToSignup Use Case.
 */
public class BackToSignupPresenter implements BackToSignupOutputBoundary {

    /**
     * ViewManagerModel.
     */
    private final ViewManagerModel viewManagerModel;
    /**
     * SignupViewModel.
     */
    private final SignupViewModel signupViewModel;

    public BackToSignupPresenter(final ViewManagerModel viewManagerModel, final SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView() {
        this.viewManagerModel.setState(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(final String error) {
    }
}
