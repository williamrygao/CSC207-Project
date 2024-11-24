package use_case.remove_from_wishlist;

import entity.Listing;
import entity.User;
import entity.UserFactory;

/**
 * The RemoveFromWishlist Interactor.
 */
public class RemoveFromWishlistInteractor implements RemoveFromWishlistInputBoundary {
    /**
     * The userDataAccessObject.
     */
    private RemoveFromWishlistUserDataAccessInterface userDataAccessObject;
    /**
     * The removeFromWishlistPresenter.
     */
    private RemoveFromWishlistOutputBoundary removeFromWishlistPresenter;
    private final UserFactory userFactory;

    /**
     * RemoveFromWishlistInteractor method.
     * @param userDataAccessInterface the userDataAccessInterface
     * @param removeFromWishlistOutputBoundary the removeFromWishlistOutputBoundary
     * @param userFactory user factory
     */
    public RemoveFromWishlistInteractor(final RemoveFromWishlistUserDataAccessInterface
                                    userDataAccessInterface,
                            final RemoveFromWishlistOutputBoundary removeFromWishlistOutputBoundary, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.removeFromWishlistPresenter = removeFromWishlistOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Override execute method.
     * @param removeFromWishlistInputData the input data
     */
    @Override
    public void execute(final RemoveFromWishlistInputData removeFromWishlistInputData) {
        final String username = removeFromWishlistInputData.getUsername();
        final String password = removeFromWishlistInputData.getPassword();
        final User user = userFactory.create(username, password);
        final Listing listing = removeFromWishlistInputData.getListing();

        userDataAccessObject.removeFromWishlist(user, listing);
        final RemoveFromWishlistOutputData removeFromWishlistOutputData = new RemoveFromWishlistOutputData(
                username, false);
        removeFromWishlistPresenter.prepareSuccessView(removeFromWishlistOutputData);
    }
}
