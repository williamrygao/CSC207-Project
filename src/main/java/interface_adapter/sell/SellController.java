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
     * @param bookID the book ID
     * @param price the price
     */
    public void execute(String username, String password, String bookID, String price) {
        final SellInputData sellInputData = new SellInputData(username, password, bookID, price);

        userSellUseCaseInteractor.execute(sellInputData);
    }

    /**
     * Gets the retail price of book if available.
     * @param bookID the book ID
     * @return a string for the retail price if available
     */
    public String getBookPrice(String bookID) {
        return userSellUseCaseInteractor.getBookPrice(bookID);
    }
}
