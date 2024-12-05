package use_case.update_listings;

import java.util.List;

import entity.listing.Listing;
import entity.user.User;

/**
 * The core interactor for the Update Listings use case, implementing the business logic
 * for updating user listings and wishlist. This class serves as the application logic layer,
 * coordinating between data access interfaces and the presenter to achieve the desired output.
 */
public class UpdateListingsInteractor implements UpdateListingsInputBoundary {
    private final UpdateListingsUserDataAccessInterface userDataAccessObject;
    private final UpdateListingsListingDataAccessInterface listingDataAccessObject;
    private final UpdateListingsOutputBoundary updateListingsPresenter;

    /**
     * Constructs the UpdateListingsInteractor with the necessary dependencies.
     *
     * @param userDataAccessObject the Data Access Object for user-related operations.
     * @param listingDataAccessObject the Data Access Object for listing-related operations.
     * @param updateListingsOutputBoundary the Output Boundary for presenting results.
     */
    public UpdateListingsInteractor(UpdateListingsUserDataAccessInterface userDataAccessObject,
                                    UpdateListingsListingDataAccessInterface listingDataAccessObject,
                                    UpdateListingsOutputBoundary updateListingsOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.listingDataAccessObject = listingDataAccessObject;
        this.updateListingsPresenter = updateListingsOutputBoundary;
    }

    /**
     * Executes the Update Listings use case, retrieving user and listing data
     * to prepare and present the updated listings and wishlist.
     *
     * @param updateListingsInputData the input data encapsulating the username.
     */
    @Override
    public void execute(UpdateListingsInputData updateListingsInputData) {
        final String username = updateListingsInputData.getUsername();

        final User user = userDataAccessObject.get(username);
        userDataAccessObject.setCurrentUsername(user.getName());
        final List<Listing> listings = listingDataAccessObject.getListings();
        final List<Listing> wishlist = userDataAccessObject.getWishlist(user);

        final UpdateListingsOutputData updateListingsOutputData = new UpdateListingsOutputData(
                user.getName(), false, listings, wishlist);
        updateListingsPresenter.prepareSuccessView(updateListingsOutputData);
    }
}
