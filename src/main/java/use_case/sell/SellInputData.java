package use_case.sell;

/**
 * The Input Data for the Sell Use Case.
 */
public class SellInputData {

    private final String username;
    private final String password;
    private final String sellingPrice;
    private final String bookID;

    public SellInputData(String username, String password, String bookID, String sellingPrice) {
        this.username = username;
        this.password = password;
        this.bookID = bookID;
        this.sellingPrice = sellingPrice;
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

    public String getSellingPrice() {
        return sellingPrice;
    }
}
