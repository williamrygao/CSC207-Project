package use_case.wishlist.remove_from_wishlist;

import entity.listing.Listing;
import entity.user.User;

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

    /**
     * RemoveFromWishlistInteractor method.
     * @param userDataAccessInterface the userDataAccessInterface
     * @param removeFromWishlistOutputBoundary the removeFromWishlistOutputBoundary
     */
    public RemoveFromWishlistInteractor(final RemoveFromWishlistUserDataAccessInterface
                                    userDataAccessInterface,
                            final RemoveFromWishlistOutputBoundary removeFromWishlistOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.removeFromWishlistPresenter = removeFromWishlistOutputBoundary;
    }

    /**
     * Override execute method.
     * @param removeFromWishlistInputData the input data
     */
    @Override
    public void execute(final RemoveFromWishlistInputData removeFromWishlistInputData) {
        final String username = removeFromWishlistInputData.getUsername();
        if (!userDataAccessObject.existsByName(username)) {
            removeFromWishlistPresenter.prepareFailView("User does not exist.");
        }
        else {
            final User user = userDataAccessObject.get(username);
            final Listing listing = removeFromWishlistInputData.getListing();

            userDataAccessObject.removeFromWishlist(user, listing);
            final RemoveFromWishlistOutputData removeFromWishlistOutputData = new RemoveFromWishlistOutputData(
                    username, false);
            removeFromWishlistPresenter.prepareSuccessView(removeFromWishlistOutputData);
        }
    }
}
