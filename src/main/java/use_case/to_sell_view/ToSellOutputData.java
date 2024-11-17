package use_case.to_sell_view;

/**
 * Output Data for the To Sell Use Case.
 */
public class ToSellOutputData {

    private String username;
    private boolean useCaseFailed;

    public ToSellOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
