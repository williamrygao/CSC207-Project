package use_case.sell;

/**
 * The Input Data for the Sell Use Case.
 */
public class SellInputData {

    private final String username;
    private final String password;
    private final String price;
    private final String bookID;

    public SellInputData(String username, String password, String bookID, String price) {
        this.username = username;
        this.password = password;
        this.bookID = bookID;
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBookID() {
        return bookID;
    }

    public String getPrice() {
        return price;
    }
}
