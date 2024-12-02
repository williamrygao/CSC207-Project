package use_case.filter_by_rating;

import java.util.List;

import entity.listing.Listing;

/**
 * DAO for the Filter By Rating Use Case.
 */
public interface FilterByRatingDataAccessInterface {

    /**
     * Returns a list of Listing objects that have ratings above the minRating.
     * @param minRating the minimum rating for the listings to be filtered by.
     * @return a list of Listing objects that have ratings above the minRating.
     */
    List<Listing> filterByRating(int minRating);
}
