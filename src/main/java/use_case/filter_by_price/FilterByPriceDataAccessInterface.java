package use_case.filter_by_price;

import java.util.List;

import entity.listing.Listing;

/**
 * DAO for the Filter By Price Use Case.
 */
public interface FilterByPriceDataAccessInterface {

    /**
     * Returns a list of all available Listing objects.
     * @return a list of all available Listing objects
     */
    List<Listing> getListings();
}
