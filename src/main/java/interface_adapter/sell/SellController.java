package interface_adapter.sell;

import use_case.sell.SellInputBoundary;
import use_case.sell.SellInputData;

/**
 * Controller for the Sell Use Case.
 */
public class SellController {
    private final SellInputBoundary userSellUseCaseInteractor;

    public SellController(SellInputBoundary userSellUseCaseInteractor) {
        this.userSellUseCaseInteractor = userSellUseCaseInteractor;
    }

    /**
     * Executes the Sell Use Case.
     * @param password the new password
     * @param username the user whose password to change
     * @param bookID the book
     */
    public void execute(String password, String username, String bookID) {
        final SellInputData sellInputData = new SellInputData(username, password, password, bookID);

        userSellUseCaseInteractor.execute(sellInputData);
    }
}
