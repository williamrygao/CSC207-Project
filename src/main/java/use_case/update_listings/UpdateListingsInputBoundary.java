package use_case.update_listings;

/**
 * Represents the Input Boundary for the Update Listings use case,
 * defining the contract for actions related to updating user listings.
 * This interface ensures adherence to the Dependency Inversion Principle,
 * enabling flexibility and testability in the application's architecture.
 */
public interface UpdateListingsInputBoundary {

    /**
     * Executes the Update Listings use case.
     * This method initiates the core logic to update the listings and user wishlist,
     * coordinating between the data access layer and the presenter.
     *
     * @param updateListingsInputData the input data encapsulating the username required for the operation.
     */
    void execute(UpdateListingsInputData updateListingsInputData);
}
