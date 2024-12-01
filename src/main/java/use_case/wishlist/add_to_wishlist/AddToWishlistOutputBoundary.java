package use_case.wishlist.add_to_wishlist;

/**
 * The Output Boundary for the Add To Wishlist Use Case.
 */
public interface AddToWishlistOutputBoundary {
    /**
     * Successful execution of the use case.
     * @param addToWishlistOutputData output data for the use case execution
     */
    void prepareSuccessView(AddToWishlistOutputData addToWishlistOutputData);

    /**
     * Use case failed.
     * @param errorMessage message to display
     */
    void prepareFailView(String errorMessage);
}
