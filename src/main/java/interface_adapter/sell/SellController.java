package interface_adapter.sell;

import use_case.sell.SellInputBoundary;
import use_case.sell.SellInputData;
import view.SellView;

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
     * @param SellingPrice the user's listed selling price for book
     */
    public void execute(String username, String password, String bookID, String SellingPrice) {
        final SellInputData sellInputData = new SellInputData(username, password, bookID, SellingPrice);

        userSellUseCaseInteractor.execute(sellInputData);
    }
}
