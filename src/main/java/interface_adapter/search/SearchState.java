package interface_adapter.search;

/**
 * The State information representing book characterization for search.
 */
public class SearchState {
    private String username = "";
    private String bookID = "";
    private String password = "";
    private String passwordError;
    private String authors;
    private String title;
    private String price;

    public SearchState(SearchState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
        authors = copy.authors;
        title = copy.title;
        price = copy.price;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SearchState() {

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

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPassword() {
        return password;
    }

    public void setBookID(String book) {
        this.bookID = book;
    }

    public String getBookID() {
        return bookID;
    }

    public String getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
