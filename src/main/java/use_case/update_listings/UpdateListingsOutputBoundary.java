package use_case.update_listings;

/**
 * The output boundary for the Update Listings Use Case.
 */
public interface UpdateListingsOutputBoundary {
    /**
     * Prepares the success view for the Update Listings Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(UpdateListingsOutputData outputData);

    /**
     * Prepares the failure view for the Update Listings Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
