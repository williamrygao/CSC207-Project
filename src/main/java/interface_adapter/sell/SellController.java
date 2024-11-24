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
     * @param username the user who is selling
     * @param password the password of the user
     * @param bookID the book
     * @param price the price
     */
    public void execute(String username, String password, String bookID, Integer price) {
        final SellInputData sellInputData = new SellInputData(username, password, password, bookID, price);

        userSellUseCaseInteractor.execute(sellInputData);
    }
}
