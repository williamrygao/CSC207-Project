package use_case.search;

import entity.listing.Listing;

import java.util.List;

/**
 * Output Data for the Search Use Case.
 */
public class SearchOutputData {

    private final String username;
    private final List<Listing> listings;

    private final boolean useCaseFailed;

    public SearchOutputData(String username, List<Listing> listings, boolean useCaseFailed) {
        this.username = username;
        this.listings = listings;
        this.useCaseFailed = useCaseFailed;
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
