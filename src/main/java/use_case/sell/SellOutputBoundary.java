package use_case.sell;

/**
 * The output boundary for the Sell Use Case.
 */
public interface SellOutputBoundary {

    /**
     * Prepares the success view for the Sell Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SellOutputData outputData);

    /**
     * Prepares the failure view for the Sell Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
