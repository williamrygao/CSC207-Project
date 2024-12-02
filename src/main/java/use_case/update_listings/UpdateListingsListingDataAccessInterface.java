package use_case.update_listings;

import java.util.List;

import entity.listing.Listing;

public interface UpdateListingsListingDataAccessInterface {
    void save(Listing listing);

    List<Listing> getListings();
}
