package interface_adapter.filter_by_price;

import use_case.filter_by_price.FilterByPriceInputBoundary;
import use_case.filter_by_price.FilterByPriceInputData;

/**
 * The controller for the Filter By Price Use Case.
 */
public class FilterByPriceController {

    private final FilterByPriceInputBoundary filterByPriceInteractor;

    public FilterByPriceController(FilterByPriceInputBoundary filterByPriceInteractor) {
        this.filterByPriceInteractor = filterByPriceInteractor;
    }

    /**
     * Executes the Filter By Price Use Case.
     * @param maxPrice the max price of books that the user wants to filter by
     */
    public void execute(int maxPrice) {
        final FilterByPriceInputData filterByPriceInputData = new FilterByPriceInputData(maxPrice);
        filterByPriceInteractor.execute(filterByPriceInputData);
    }
}

