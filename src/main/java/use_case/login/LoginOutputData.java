package use_case.login;

import java.util.List;

import entity.Listing;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String username;
    private final boolean useCaseFailed;
    private final List<Listing> listings;
    private final List<Listing> wishlist;

    public LoginOutputData(String username, boolean useCaseFailed, List<Listing> listings, List<Listing> wishlist) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.listings = listings;
        this.wishlist = wishlist;
    }

    public String getUsername() {
        return username;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public List<Listing> getWishlist() {
        return wishlist;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
