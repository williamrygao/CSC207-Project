package use_case.view_wishlist;

/**
 * The View Wishlist Interactor.
 */
public class ViewWishlistInteractor implements ViewWishlistInputBoundary {
    private ViewWishlistOutputBoundary viewWishlistPresenter;

    public ViewWishlistInteractor(final ViewWishlistOutputBoundary viewWishlistPresenter) {
        this.viewWishlistPresenter = viewWishlistPresenter;
    }

    @Override
    public void execute() {
        viewWishlistPresenter.prepareSuccessView();
    }
}
