package use_case.update_listings;

import java.util.List;

import entity.listing.Listing;
import entity.user.User;

/**
 * The Update Listings Interactor.
 */
public class UpdateListingsInteractor implements UpdateListingsInputBoundary {
    private final UpdateListingsUserDataAccessInterface userDataAccessObject;
    private final UpdateListingsListingDataAccessInterface listingDataAccessObject;
    private final UpdateListingsOutputBoundary updateListingsPresenter;

    public UpdateListingsInteractor(UpdateListingsUserDataAccessInterface userDataAccessObject,
                                    UpdateListingsListingDataAccessInterface listingDataAccessObject,
                                    UpdateListingsOutputBoundary updateListingsOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.listingDataAccessObject = listingDataAccessObject;
        this.updateListingsPresenter = updateListingsOutputBoundary;
    }

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
