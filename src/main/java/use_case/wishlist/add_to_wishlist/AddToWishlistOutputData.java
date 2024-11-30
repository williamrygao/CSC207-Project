package use_case.wishlist.add_to_wishlist;

/**
 * Output Data for the Remove from wishlist Use Case.
 */
public class AddToWishlistOutputData {
    private String username;
    private boolean useCaseFailed;

    public AddToWishlistOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
