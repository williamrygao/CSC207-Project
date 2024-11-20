package use_case.sell;

/**
 * The Input Data for the Sell Use Case.
 */
public class SellInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final Integer price;

    private final String bookID;

    public SellInputData(String username, String password, String repeatPassword, String bookID, Integer price) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.bookID = bookID;
        this.price = price;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getBookID() {
        return bookID;
    }

    public Integer getPrice() {
    }
}
