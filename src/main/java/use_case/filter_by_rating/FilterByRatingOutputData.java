package use_case.filter_by_rating;

import java.util.List;

import entity.Listing;

/**
 * Output Data for the Filter By Rating Use Case.
 */

public class FilterByRatingOutputData {
    private final List<Listing> listings;

    public FilterByRatingOutputData(List<Listing> listings) {
        this.listings = listings;
    }

    public List<Listing> getBooks() {
        return listings;
    }
}
