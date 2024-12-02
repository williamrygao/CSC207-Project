
package use_case.filter_by_genre;

import java.util.List;

import entity.listing.Listing;

/**
 * Interface for data access related to filtering listings by genre.
 */
public interface FilterByGenreDataAccessInterface {

    /**
     * Retrieves listings that match the given genre search term (case-insensitive, partial matches allowed).
     *
     * @param genre the genre search term
     * @return a list of listings that match the genre
     */
    List<Listing> getListingsByGenre(String genre);
}
