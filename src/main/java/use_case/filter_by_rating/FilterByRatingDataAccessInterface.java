package use_case.filter_by_rating;

import java.util.List;

import entity.listing.Listing;
import entity.book.Book;

public interface FilterByRatingDataAccessInterface {
    List<Listing> filterByRating(int rating);
}
