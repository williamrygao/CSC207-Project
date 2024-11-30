package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * The View Model for the Home View.
 */
public class HomeViewModel extends ViewModel<HomeState> {

    public HomeViewModel() {
        super("home");
        setState(new HomeState());
    }

}
