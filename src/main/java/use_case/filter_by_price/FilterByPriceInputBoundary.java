package use_case.filter_by_price;

/**
 * Input Boundary for actions which are related to filtering listings by price.
 */
public interface FilterByPriceInputBoundary {

    /**
     * Executes the filter by price use case.
     * @param filterByPriceInputData the input data
     */
    void execute(FilterByPriceInputData filterByPriceInputData);
}
