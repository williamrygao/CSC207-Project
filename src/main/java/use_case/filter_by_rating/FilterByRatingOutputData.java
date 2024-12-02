package use_case.filter_by_rating;

import java.util.List;

import entity.listing.Listing;
/**
 * Output Data for the Filter By Rating Use Case.
 */

public class FilterByRatingOutputData {
    private final List<Listing> books;

    public FilterByRatingOutputData(List<Listing> books) {
        this.books = books;
    }

    public List<Listing> getBooks() {
        return books;
    }
}