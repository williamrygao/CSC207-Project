package use_case.wishlist.remove_from_wishlist;

/**
 * Input Boundary for actions which are related to removing from wish list.
 */
public interface RemoveFromWishlistInputBoundary {

    /**
     * Executes the Remove from wishlist use case.
     * @param removeFromWishlistInputData the input data
     */
    void execute(RemoveFromWishlistInputData removeFromWishlistInputData);
}
