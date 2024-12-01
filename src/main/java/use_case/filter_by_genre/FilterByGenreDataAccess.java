package use_case.filter_by_genre;

import java.util.ArrayList;
import java.util.List;

import data_access.FirebaseListingDataAccessObject;
import entity.Listing;
import entity.book.Book;

public class FilterByGenreDataAccess implements FilterByGenreDataAccessInterface {
    private final FirebaseListingDataAccessObject firebaseDao;

    /**
     * Constructor to initialize the Firebase DAO.
     *
     * @param firebaseDao the data access object for Firebase
     */
    public FilterByGenreDataAccess(FirebaseListingDataAccessObject firebaseDao) {
        this.firebaseDao = firebaseDao;
    }

    /**
     * Retrieves listings that match the given genre search term (case-insensitive, partial matches allowed).
     *
     * @param genre the genre search term
     * @return a list of listings that match the genre
     */
    @Override
    public List<Listing> getListingsByGenre(String genre) {
        final String searchGenre = genre.toLowerCase().trim();
        final List<Listing> allListings = firebaseDao.getListings();
        final List<Listing> filteredListings = new ArrayList<>();

        for (Listing listing : allListings) {
            final Book book = listing.getBook();
            final String bookGenre = book.getGenre().toLowerCase();
            if (bookGenre.contains(searchGenre)) {
                filteredListings.add(listing);
            }
        }

        return filteredListings;
    }
}
