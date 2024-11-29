package use_case.login;

import java.util.List;

import entity.Listing;
import entity.User;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginListingDataAccessInterface listingDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessObject,
                           LoginListingDataAccessInterface listingDataAccessObject,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.listingDataAccessObject = listingDataAccessObject;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userDataAccessObject.get(username);
                userDataAccessObject.setCurrentUsername(user.getName());

                final List<Listing> listings = listingDataAccessObject.getListings();
                final List<Listing> wishlist = userDataAccessObject.getWishlist(user);

                final LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false, listings, wishlist);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}
