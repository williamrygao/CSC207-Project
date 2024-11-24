package use_case.add_to_wishlist;

import entity.Listing;
import entity.User;
import entity.UserFactory;

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
    private final UserFactory userFactory;

    /**
     * AddToWishlistInteractor method.
     * @param userDataAccessInterface the userDataAccessInterface
     * @param addToWishlistOutputBoundary the AddToWishlistOutputBoundary
     * @param userFactory user factory
     */
    public AddToWishlistInteractor(final AddToWishlistUserDataAccessInterface
                                    userDataAccessInterface,
                                        final AddToWishlistOutputBoundary addToWishlistOutputBoundary, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.addToWishlistPresenter = addToWishlistOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Override execute method.
     * @param addToWishlistInputData the input data
     */
    @Override
    public void execute(final AddToWishlistInputData addToWishlistInputData) {
        final String username = addToWishlistInputData.getUsername();
        final String password = addToWishlistInputData.getPassword();
        final User user = userFactory.create(username, password);
        final Listing listing = addToWishlistInputData.getListing();

        userDataAccessObject.addToWishlist(user, listing);
        final AddToWishlistOutputData addToWishlistOutputData = new AddToWishlistOutputData(
                username, false);
        addToWishlistPresenter.prepareSuccessView(addToWishlistOutputData);
    }
}
