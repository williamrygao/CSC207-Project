package use_case.login;

import entity.listing.Listing;

import java.util.List;

public interface LoginListingDataAccessInterface {
    void save(Listing listing);

    List<Listing> getListings();
}
