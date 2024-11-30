package use_case.wishlist.add_to_wishlist;

/**
 * Input Boundary for actions which are related to removing from wish list.
 */
public interface AddToWishlistInputBoundary {

    /**
     * Executes the Remove from wishlist use case.
     * @param addToWishlistInputData the input data
     */
    void execute(AddToWishlistInputData addToWishlistInputData);
}
