package use_case.wishlist.remove_from_wishlist;

/**
 * Output Data for the Remove from wishlist Use Case.
 */
public class RemoveFromWishlistOutputData {
    private String username;
    private boolean useCaseFailed;

    public RemoveFromWishlistOutputData(String username, boolean useCaseFailed) {
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
