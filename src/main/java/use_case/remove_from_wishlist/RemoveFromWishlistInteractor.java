package use_case.remove_from_wishlist;

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
        // * get the username out of the input data,
        // * set the username to null in the DAO
        // * instantiate the `RemoveFromWishlistOutputData`,which needs to contain username.
        // * tell the presenter to prepare a success view.
        final String username = removeFromWishlistInputData.getUsername();

        // I (Victor) added this fail condition but unsure if needed
        if (username == null) {
            removeFromWishlistPresenter.prepareFailView("Username not found.");
        }
        else {
            userDataAccessObject.setCurrentUsername(null);

            // I'm pretty sure the useCaseFailed argument is supposed to be
            // false unless I interpreted it wrong
            // Documenting here just in case (delete if all tests pass)
            final RemoveFromWishlistOutputData removeFromWishlistOutputData = new RemoveFromWishlistOutputData(
                    username, false);
            removeFromWishlistPresenter.prepareSuccessView(removeFromWishlistOutputData);
        }
    }
}

