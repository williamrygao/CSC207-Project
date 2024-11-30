package interface_adapter.update_listings;

import use_case.update_listings.UpdateListingsInputBoundary;
import use_case.update_listings.UpdateListingsInputData;

/**
 * The controller for the Update Listings Use Case.
 */
public class UpdateListingsController {

    private final UpdateListingsInputBoundary updateListingsInteractor;

    public UpdateListingsController(UpdateListingsInputBoundary updateListingsInteractor) {
        this.updateListingsInteractor = updateListingsInteractor;
    }

    /**
     * Executes the Update Listings Use Case.
     * @param username the username of the user logging in
     */
    public void execute(String username) {
        final UpdateListingsInputData updateListingsInputData = new UpdateListingsInputData(
                username);

        updateListingsInteractor.execute(updateListingsInputData);
    }
}
