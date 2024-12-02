package interface_adapter.filter_by_price;

import entity.listing.Listing;

import java.util.ArrayList;
import java.util.List;

/**
 * The state representing the filter by price process.
 */
public class FilterByPriceState {
    private String username = "";
    private String password = "";
    private int maxPrice;
    private List<Listing> listings;

    public FilterByPriceState(FilterByPriceState copy) {
        this.username = copy.getUsername();
        this.password = copy.getPassword();
    }

    public FilterByPriceState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }
}
