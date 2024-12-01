package use_case.filter_by_genre;

import java.util.List;

import entity.Listing;

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
        this.filteredListings = filteredListings;
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
