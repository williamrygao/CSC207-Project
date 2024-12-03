package use_case.view_wishlist;

public interface ViewWishlistOutputBoundary {
    void prepareSuccessView(ViewWishlistOutputData viewWishlistOutputData);

    void prepareFailView(String errorMessage);
}
