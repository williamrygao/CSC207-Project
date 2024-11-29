package use_case.add_to_wishlist;

import entity.Listing;
import entity.User;

/**
 * The AddToWishlist Interactor.
 */
public class AddToWishlistInteractor implements AddToWishlistInputBoundary {
    /**
     * The userDataAccessObject.
     */
    private AddToWishlistUserDataAccessInterface userDataAccessObject;
    /**
     * The AddToWishlistPresenter.
     */
    private AddToWishlistOutputBoundary addToWishlistPresenter;

    /**
     * AddToWishlistInteractor method.
     * @param userDataAccessInterface the userDataAccessInterface
     * @param addToWishlistOutputBoundary the AddToWishlistOutputBoundary
     */
    public AddToWishlistInteractor(final AddToWishlistUserDataAccessInterface
                                    userDataAccessInterface,
                                        final AddToWishlistOutputBoundary addToWishlistOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.addToWishlistPresenter = addToWishlistOutputBoundary;
    }

    /**
     * Override execute method.
     * @param addToWishlistInputData the input data
     */
    @Override
    public void execute(final AddToWishlistInputData addToWishlistInputData) {
        final String username = addToWishlistInputData.getUsername();
        final User user = userDataAccessObject.get(username);
        final Listing listing = addToWishlistInputData.getListing();

        userDataAccessObject.addToWishlist(user, listing);
        final AddToWishlistOutputData addToWishlistOutputData = new AddToWishlistOutputData(
                username, false);
        addToWishlistPresenter.prepareSuccessView(addToWishlistOutputData);
    }
}
