package use_case.wishlist.view_wishlist;

import java.util.List;

import entity.listing.Listing;
import entity.user.User;

/**
 * The View Wishlist Interactor.
 */
public class ViewWishlistInteractor implements ViewWishlistInputBoundary {
    private final ViewWishlistUserDataAccessInterface userDataAccessObject;
    private ViewWishlistOutputBoundary viewWishlistPresenter;

    public ViewWishlistInteractor(ViewWishlistUserDataAccessInterface userDataAccessObject,
                                  ViewWishlistOutputBoundary viewWishlistPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.viewWishlistPresenter = viewWishlistPresenter;
    }

    @Override
    public void execute(ViewWishlistInputData viewWishlistInputData) {
        final String username = viewWishlistInputData.getUsername();
        if (!userDataAccessObject.existsByName(username)) {
            viewWishlistPresenter.prepareFailView("User does not exist.");
        }
        else {
            final User user = userDataAccessObject.get(username);
            final List<Listing> wishlist = userDataAccessObject.getWishlist(user);

            final ViewWishlistOutputData viewWishlistOutputData = new ViewWishlistOutputData(
                    username,
                    wishlist,
                    false
            );
            viewWishlistPresenter.prepareSuccessView(viewWishlistOutputData);
        }
    }
}
