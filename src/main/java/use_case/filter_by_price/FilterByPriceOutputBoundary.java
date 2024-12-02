package use_case.filter_by_price;

public interface FilterByPriceOutputBoundary {
    void prepareSuccessView(FilterByPriceOutputData filterByPriceOutputData);

    void prepareFailView(String errorMessage);
}
