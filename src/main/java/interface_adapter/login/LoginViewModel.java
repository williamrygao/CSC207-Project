package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class LoginViewModel extends ViewModel<LoginState> {

    /**
     * String TITLE_LABEL = "Sign Up View".
     */
    public static final String TITLE_LABEL = "Login Screen";
    /**
     * String USERNAME_LABEL = "Choose username".
     */
    public static final String USERNAME_LABEL = "Username";
    /**
     * String PASSWORD_LABEL = "Choose password".
     */
    public static final String PASSWORD_LABEL = "Password";

    /**
     * String BACK_BUTTON_LABEL = "Back".
     */
    public static final String BACK_BUTTON_LABEL = "Back";
    /**
     * String LOGIN_BUTTON_LABEL = "Login".
     */
    public static final String LOGIN_BUTTON_LABEL = "Login";

    /**
     * String TO_LOGIN_BUTTON_LABEL = "Go to Login".
     */
    public static final String TO_LOGIN_BUTTON_LABEL = "Go to Login";

    public LoginViewModel() {
        super("Login");
        setState(new LoginState());
    }

}
