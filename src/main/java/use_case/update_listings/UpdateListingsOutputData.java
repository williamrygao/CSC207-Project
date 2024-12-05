package use_case.update_listings;

import java.util.List;

import entity.listing.Listing;

/**
 * A data structure that encapsulates the output data of the Update Listings use case.
 * This class adheres to the Data Transfer Object (DTO) design pattern,
 * facilitating the transfer of results to the presentation layer.
 */

public class UpdateListingsOutputData {

    private final String username;
    private final boolean useCaseFailed;
    private final List<Listing> listings;
    private final List<Listing> wishlist;

    /**
     * Constructs an UpdateListingsOutputData object.
     *
     * @param username the username of the user associated with this output data.
     * @param useCaseFailed a boolean indicating whether the use case execution failed.
     * @param listings the updated list of all available listings.
     * @param wishlist the updated wishlist for the user.
     */

    public UpdateListingsOutputData(String username, boolean useCaseFailed, List<Listing> listings,
                                    List<Listing> wishlist) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.listings = listings;
        this.wishlist = wishlist;
    }

    /**
     * Retrieves the username associated with this output data.
     *
     * @return the username.
     */

    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the list of updated listings.
     *
     * @return the list of listings.
     */

    public List<Listing> getListings() {
        return listings;
    }

    /**
     * Retrieves the updated wishlist for the user.
     *
     * @return the wishlist.
     */

    public List<Listing> getWishlist() {
        return wishlist;
    }

    /**
     * Checks whether the use case execution failed.
     *
     * @return true if the use case failed; false otherwise.
     */

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
