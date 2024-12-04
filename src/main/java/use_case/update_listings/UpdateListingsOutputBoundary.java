package use_case.update_listings;

/**
 * Represents the Output Boundary for the Update Listings use case,
 * defining the contract for presenting success and failure scenarios to the user.
 */
public interface UpdateListingsOutputBoundary {
    
    /**
     * Prepares the success view for the Update Listings use case.
     *
     * @param outputData the output data containing the updated listings and wishlist information.
     */
    void prepareSuccessView(UpdateListingsOutputData outputData);

    /**
     * Prepares the failure view for the Update Listings use case.
     */
    default void prepareFailView() {
        prepareFailView(null);
    }

    /**
     * Prepares the failure view for the Update Listings use case.
     *
     * @param errorMessage the explanation of the failure, to be displayed to the user.
     */
    void prepareFailView(String errorMessage);
}
