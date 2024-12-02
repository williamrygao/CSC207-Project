package use_case.update_listings;

/**
 * Input Boundary for actions which are related to updating listings.
 */
public interface UpdateListingsInputBoundary {

    /**
     * Executes the update listings use case.
     * @param updateListingsInputData the input data
     */
    void execute(UpdateListingsInputData updateListingsInputData);
}
