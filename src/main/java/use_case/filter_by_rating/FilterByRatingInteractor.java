package use_case.filter_by_rating;

import java.util.List;

import entity.Book;

/**
 * The Filter By Rating Interactor.
 */
public class FilterByRatingInteractor implements FilterByRatingInputBoundary {
    private final FilterByRatingDataAccessInterface filterByRatingDataAccessObject;
    private final use_case.filterRating.FilterByRatingOutputBoundary filterByRatingPresenter;

    public FilterByRatingInteractor(FilterByRatingDataAccessInterface filterByRatingDataAccessInterface,
                                    use_case.filterRating.FilterByRatingOutputBoundary filterByRatingOutputBoundary) {
        this.filterByRatingDataAccessObject = filterByRatingDataAccessInterface;
        this.filterByRatingPresenter = filterByRatingOutputBoundary;
    }

    @Override
    public void execute(FilterByRatingInputData filterByRatingInputData) {
        // retrieve rating to filter by
        final int rating = filterByRatingInputData.getRating();

        // retrieve books in database with that rating or higher
        final List<Book> books = filterByRatingDataAccessObject.filterByRating(rating);

        // prepare views using the presenter
        final FilterByRatingOutputData filterByRatingOutputData = new FilterByRatingOutputData(books);

    }

}
