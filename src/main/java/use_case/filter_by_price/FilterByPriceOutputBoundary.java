package use_case.filter_by_price;

/**
 * Output Boundary for actions which are related to filtering listings by price.
 */
public interface FilterByPriceOutputBoundary {

    /**
     * Prepares the success view for filter by price use case.
     * @param filterByPriceOutputData the output data
     */
    void prepareSuccessView(FilterByPriceOutputData filterByPriceOutputData);

    /**
     * Prepares the failure view for filter by price use case.
     * @param errorMessage the error message
     */
    void prepareFailView(String errorMessage);
}
