package use_case.update_listings;

import java.util.List;

import entity.listing.Listing;

/**
 * Represents the Data Access Object (DAO) interface for listing-related operations
 * in the Update Listings use case. This interface abstracts the storage and retrieval
 * of listings, enabling flexibility in the underlying data storage implementation.
 */
public interface UpdateListingsListingDataAccessInterface {

    /**
     * Saves a new or updated listing to the data storage.
     *
     * @param listing the Listing object to be saved. It can represent a new listing or an update to an existing one.
     */
    void save(Listing listing);

    /**
     * Retrieves all available listings from the data storage.
     *
     * @return a list of Listing objects representing all current listings.
     */
    List<Listing> getListings();
}
