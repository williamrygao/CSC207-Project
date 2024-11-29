package use_case.wishlist.add_to_wishlist;

public interface AddToWishlistOutputBoundary {
    void prepareSuccessView(AddToWishlistOutputData addToWishlistOutputData);

    void prepareFailView(String errorMessage);
}
