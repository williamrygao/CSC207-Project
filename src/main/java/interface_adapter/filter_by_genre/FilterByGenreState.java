package interface_adapter.filter_by_genre;

import java.util.List;

import entity.listing.Listing;

/**
 * Represents the UI state for the "Filter Books by Genre" functionality.
 */
public class FilterByGenreState {
    private String username = "";
    private String password = "";
    private String genre;
    private List<Listing> listings;

    public FilterByGenreState(FilterByGenreState copy) {
        this.username = copy.getUsername();
        this.password = copy.getPassword();
    }

    public FilterByGenreState() {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }
}