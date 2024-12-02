package use_case.filter_by_rating;

import java.util.List;

import entity.listing.Listing;

/**
 * The Filter By Rating Interactor.
 */
public class FilterByRatingInteractor implements FilterByRatingInputBoundary {
    private final FilterByRatingDataAccessInterface filterByRatingDataAccessObject;
    private final FilterByRatingOutputBoundary filterByRatingPresenter;

    public FilterByRatingInteractor(FilterByRatingDataAccessInterface filterByRatingDataAccessInterface,
                                    FilterByRatingOutputBoundary filterByRatingOutputBoundary) {
        this.filterByRatingDataAccessObject = filterByRatingDataAccessInterface;
        this.filterByRatingPresenter = filterByRatingOutputBoundary;
    }

    @Override
    public void execute(FilterByRatingInputData filterByRatingInputData) {
        // retrieve rating to filter by
        final int rating = filterByRatingInputData.getRating();

        // retrieve books in database with that rating or higher
        final List<Listing> books = filterByRatingDataAccessObject.filterByRating(rating);

        // prepare views using the presenter
        final FilterByRatingOutputData filterByRatingOutputData = new FilterByRatingOutputData(books);

    }

}
