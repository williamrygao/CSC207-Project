package use_case.filter_by_rating;

import entity.Book;

import java.util.List;

public interface FilterByRatingDataAccessInterface {
    List<Book> filterByRating(int rating);
}
