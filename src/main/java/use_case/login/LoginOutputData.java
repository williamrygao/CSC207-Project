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

    public LoginOutputData(String username, boolean useCaseFailed, List<Listing> listings) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.listings = listings;
    }

    public String getUsername() {
        return username;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
