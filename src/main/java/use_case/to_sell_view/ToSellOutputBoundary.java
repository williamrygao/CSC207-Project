package use_case.to_sell_view;

/**
 * The output boundary for the To Sell View Use Case.
 */
public interface ToSellOutputBoundary {
    /**
     * Prepares the success view for the To Sell Use Case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
