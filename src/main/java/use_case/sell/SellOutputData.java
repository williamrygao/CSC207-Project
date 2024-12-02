package use_case.sell;

import entity.listing.Listing;

/**
 * Output Data for the Sell Use Case.
 */
public class SellOutputData {

    private final String username;
    private final Listing listing;

    private final boolean useCaseFailed;

    public SellOutputData(String username, Listing listing, boolean useCaseFailed) {
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
