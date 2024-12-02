package use_case.search;

import entity.listing.Listing;

/**
 * Output Data for the Search Use Case.
 */
public class SearchOutputData {

    private final String username;
    private final Listing listing;

    private final boolean useCaseFailed;

    public SearchOutputData(String username, Listing listing, boolean useCaseFailed) {
        this.username = username;
        this.listing = listing;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public Listing getListing() {
        return listing;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
