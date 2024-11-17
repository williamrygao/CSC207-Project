package use_case.to_sell_view;

/**
 * The Input Data for the To Sell View Use Case.
 */
public class ToSellInputData {
    private final String username;

    public ToSellInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
