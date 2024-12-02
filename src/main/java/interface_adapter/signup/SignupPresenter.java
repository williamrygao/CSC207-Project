package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class SignupPresenter implements SignupOutputBoundary {

    /**
     * SignupViewModel.
     */
    private final SignupViewModel signupViewModel;
    /**
     * LoginViewModel.
     */
    private final LoginViewModel loginViewModel;
    /**
     * ViewManagerModel.
     */
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a SignupPresenter with specified view models and view manager.
     * @param viewManagerModel the model that manages the current view state
     * @param signupViewModel the view model representing the signup process
     * @param loginViewModel the view model representing the login state
     */
    public SignupPresenter(final ViewManagerModel viewManagerModel,
                           final SignupViewModel signupViewModel,
                           final LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Override prepareSuccessView method.
     * @param response the output data
     */
    @Override
    public void prepareSuccessView(final SignupOutputData response) {
        // On success, switch to the login view.
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Override prepareFailView method.
     * @param error the explanation of the failure
     */
    @Override
    public void prepareFailView(final String error) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setSignupError(error);
        signupViewModel.firePropertyChanged();
    }

    /**
     * Override switchToLoginView method.
     */
    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
