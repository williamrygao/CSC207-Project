package interface_adapter.sell;

/**
 * The State information representing the selling user.
 */
public class SellState {
    private String username = "";
    private String bookID = "";
    private String password = "";
    private String passwordError;
    private String price;
    private String sellError;

    public SellState(SellState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
        price = copy.price;
        sellError = copy.sellError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SellState() {

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

    public String getPrice() {
        return price;
    }

    public void setSellError(String error) {
        this.sellError = error;
    }

    public String getSellError() {
        return sellError;
    }
}
