package use_case.filter_by_price;

import java.util.List;

import entity.listing.Listing;

/**
 * Output Data for the Filter By Price Use Case.
 */

public class FilterByPriceOutputData {
    private final List<Listing> listings;

    public FilterByPriceOutputData(List<Listing> listings) {
        this.listings = listings;
    }

    public List<Listing> getListings() {
        return listings;
    }
}
