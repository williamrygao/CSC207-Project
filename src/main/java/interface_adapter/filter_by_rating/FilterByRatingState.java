package interface_adapter.filter_by_rating;

/**
 * The state representing the filter by rating process.
 */
public class FilterByRatingState {
    private String username = "";
    private String password = "";
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

    public int getMinRating() {
        return minRating;
    }

    public void setMinRating(int minRating) {
        this.minRating = minRating;
    }
}
