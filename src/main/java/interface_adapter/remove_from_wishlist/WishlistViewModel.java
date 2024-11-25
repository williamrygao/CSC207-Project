package interface_adapter.remove_from_wishlist;

import interface_adapter.ViewModel;

/**
 * The View Model for the Sell View.
 */
public class WishlistViewModel extends ViewModel<WishlistState> {

    public WishlistViewModel() {
        super("wishlist");
        setState(new WishlistState());
    }

}
