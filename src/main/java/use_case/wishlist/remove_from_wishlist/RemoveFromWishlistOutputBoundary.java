package use_case.wishlist.remove_from_wishlist;

public interface RemoveFromWishlistOutputBoundary {
    void prepareSuccessView(RemoveFromWishlistOutputData removeFromWishlistOutputData);

    void prepareFailView(String errorMessage);
}
