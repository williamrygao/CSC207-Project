package use_case.filter_by_price;

import java.util.ArrayList;
import java.util.List;

import entity.listing.Listing;

/**
 * The Filter By Price Interactor.
 */
public class FilterByPriceInteractor implements FilterByPriceInputBoundary {
    private final FilterByPriceDataAccessInterface filterByPriceDataAccessObject;
    private final FilterByPriceOutputBoundary filterByRatingPresenter;

    public FilterByPriceInteractor(FilterByPriceDataAccessInterface filterByPriceDataAccessInterface,
                                   FilterByPriceOutputBoundary filterByPriceOutputBoundary) {
        this.filterByPriceDataAccessObject = filterByPriceDataAccessInterface;
        this.filterByRatingPresenter = filterByPriceOutputBoundary;
    }

    @Override
    public void execute(FilterByPriceInputData filterByPriceInputData) {
        // retrieve max price to filter by
        final int maxPrice = filterByPriceInputData.getPrice();

        // retrieve books in database with that price or lower
        if (filterByPriceDataAccessObject.getListings().isEmpty()) {
            // if there are no listings yet prepare a fail view
            this.filterByRatingPresenter.prepareFailView("No listings yet.");
        }
        else {
            // pass output data to the presenter and prepare success view
            final List<Listing> allListings = filterByPriceDataAccessObject.getListings();
            final List<Listing> filteredListings = new ArrayList<>();
            for (Listing listing : allListings) {
                if (Integer.parseInt(listing.getPrice()) <= maxPrice) {
                    filteredListings.add(listing);
                }
            }
            final FilterByPriceOutputData filterByPriceOutputData = new FilterByPriceOutputData(filteredListings);
            this.filterByRatingPresenter.prepareSuccessView(filterByPriceOutputData);
        }
    }
}
