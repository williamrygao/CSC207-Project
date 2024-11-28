package use_case.leave_rating;

import entity.UserFactory;
import use_case.add_to_wishlist.AddToWishlistOutputBoundary;
import use_case.add_to_wishlist.AddToWishlistUserDataAccessInterface;

public class LeaveRatingInteractor implements LeaveRatingInputBoundary {
    /**
     * The userDataAccessObject.
     */
    private LeaveRatingDataAccessInterface userDataAccessObject;
    /**
     * The AddToWishlistPresenter.
     */
    private AddToWishlistOutputBoundary addToWishlistPresenter;
    private final UserFactory userFactory;


}
