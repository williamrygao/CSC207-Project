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

    private final List<Listing> filteredListings;

    /**
     * Constructor to initialize the filtered book listings.
     *
     * @param filteredListings the list of filtered book listings
     */

    public FilterByGenreOutputData(List<Listing> filteredListings) {
        this.filteredListings = Objects.requireNonNullElseGet(filteredListings, ArrayList::new);
    }

    /**
     * Returns the filtered book listings.
     *
     * @return a list of filtered book listings
     */

    public List<Listing> getFilteredListings() {
        return filteredListings;
    }
}
