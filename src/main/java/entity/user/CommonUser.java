package entity.user;

import java.util.ArrayList;
import java.util.List;

import entity.listing.Listing;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;

    private List<Listing> wishlist;
    private List<Listing> listings;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.wishlist = new ArrayList<>();
        this.listings = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public List<Listing> getWishlist() {
        return wishlist;
    }

    public void addToWishlist(Listing listing) {
        this.wishlist.add(listing);
    }

    public void addToListings(Listing listing) {
        this.listings.add(listing);
    }
}
