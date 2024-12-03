package interface_adapter.change_password;

import java.util.List;

import entity.Listing;

/**
 * The State information representing the logged-in user.
 */
public class HomeState {
    private String username = "";

    private String password = "";
    private String passwordError;

    private List<Listing> listings;

    public HomeState(HomeState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
        listings = copy.listings;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public HomeState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    /**
     * Add a new listing.
     * @param listing the new listing
     */
    public void addListing(Listing listing) {
        this.listings.add(listing);
    }
}
