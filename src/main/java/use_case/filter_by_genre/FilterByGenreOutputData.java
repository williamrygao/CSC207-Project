package use_case.filter_by_genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entity.listing.Listing;

/**
 * The output data for the "Filter Books by Genre" use case.
 * Contains the filtered book listings.
 */
public class FilterByGenreOutputData {

    private final List<Listing> Listings;

    /**
     * Constructor to initialize the filtered book listings.
     *
     * @param Listings the list of filtered book listings
     */
    public FilterByGenreOutputData(List<Listing> Listings) {
        this.Listings = Objects.requireNonNullElseGet(Listings, ArrayList::new);
    }

    /**
     * Returns the filtered book listings.
     *
     * @return a list of filtered book listings
     */
    public List<Listing> getListings() {
        return Listings;
    }
}