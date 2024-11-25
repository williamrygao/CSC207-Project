package interface_adapter.sell;

/**
 * The State information representing the selling user.
 */
public class SellState {
    private String username = "";
    private String bookID = "";
    private String sellingPrice = "";
    private String password = "";
    private String passwordError;

    public SellState(SellState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
        sellingPrice = copy.sellingPrice;
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

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
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

    /**
     * The setSellingPrice method.
     */
    public void setSellingPrice() {
        this.sellingPrice = sellingPrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }
}
