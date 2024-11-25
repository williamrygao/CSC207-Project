package use_case.view_wishlist;

public interface ViewWishlistOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String errorMessage);
}
