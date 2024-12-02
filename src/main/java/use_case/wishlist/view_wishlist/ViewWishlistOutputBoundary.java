package use_case.wishlist.view_wishlist;

public interface ViewWishlistOutputBoundary {
    void prepareSuccessView(ViewWishlistOutputData viewWishlistOutputData);

    void prepareFailView(String errorMessage);
}
