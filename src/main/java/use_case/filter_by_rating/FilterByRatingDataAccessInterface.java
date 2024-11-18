package use_case.filter_by_rating;

import java.util.List;

import entity.Book;

public interface FilterByRatingDataAccessInterface {
    List<Book> filterByRating(int rating);
}
