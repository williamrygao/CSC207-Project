package use_case.wishlist.add_to_wishlist;

import entity.listing.Listing;
import entity.user.User;

/**
 * The AddToWishlist Interactor.
 */
public class AddToWishlistInteractor implements AddToWishlistInputBoundary {
    /**
     * The Add to Wishlist User data access object.
     */
    private final AddToWishlistUserDataAccessInterface userDataAccessObject;
    /**
     * The Add to Wishlist output boundary.
     */
    private final AddToWishlistOutputBoundary addToWishlistPresenter;

    /**
     * AddToWishlistInteractor method.
     * @param userDataAccessObject the Add to Wishlist User data access object
     * @param addToWishlistOutputBoundary the Add to Wishlist output boundary
     */
    public AddToWishlistInteractor(
            final AddToWishlistUserDataAccessInterface userDataAccessObject,
            final AddToWishlistOutputBoundary addToWishlistOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.addToWishlistPresenter = addToWishlistOutputBoundary;
    }

    /**
     * Override execute method.
     * @param addToWishlistInputData the Add to Wishlist input data
     */
    @Override
    public void execute(final AddToWishlistInputData addToWishlistInputData) {
        final String username = addToWishlistInputData.getUsername();

        if (!userDataAccessObject.existsByName(username)) {
            addToWishlistPresenter.prepareFailView("User does not exist.");
        }
        else {
            final User user = userDataAccessObject.get(username);
            final Listing listing = addToWishlistInputData.getListing();

            userDataAccessObject.addToWishlist(user, listing);
            final AddToWishlistOutputData addToWishlistOutputData = new AddToWishlistOutputData(
                    username, false
            );
            addToWishlistPresenter.prepareSuccessView(addToWishlistOutputData);
        }
    }
}
