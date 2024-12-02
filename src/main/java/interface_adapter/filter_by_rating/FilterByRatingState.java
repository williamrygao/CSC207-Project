package interface_adapter.filter_by_rating;

import entity.listing.Listing;

import java.util.List;

/**
 * The state representing the filter by rating process.
 */
public class FilterByRatingState {
    private String username = "";
    private String password = "";
    private String filterError;
    private int minRating;

    public FilterByRatingState(FilterByRatingState copy) {
        this.username = copy.getUsername();
        this.password = copy.getPassword();
    }

    public FilterByRatingState() {

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

    public String getFilterError() {
        return filterError;
    }

    public void setFilterError(String filterError) {
        this.filterError = filterError;
    }

    public int getMinRating() {
        return minRating;
    }

    public void setMinRating(int minRating) {
        this.minRating = minRating;
    }
}
