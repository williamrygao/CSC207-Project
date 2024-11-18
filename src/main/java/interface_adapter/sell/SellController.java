package interface_adapter.sell;

import use_case.sell.SellBookDataFetcher;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInputData;
import view.SellView;

/**
 * Controller for the Sell Use Case.
 */
public class SellController {
    private final SellInputBoundary userSellUseCaseInteractor;
    private final SellView sellView;

    public SellController(SellInputBoundary userSellUseCaseInteractor, SellView sellView) {
        this.userSellUseCaseInteractor = userSellUseCaseInteractor;
        this.sellView = sellView;
    }

    /**
     * Executes the Sell Use Case.
     * @param username the user who is selling
     * @param password the password of the user
     * @param bookID the book
     */
    public void execute(String username, String password, String bookID) {
        final SellInputData sellInputData = new SellInputData(username, password, password, bookID);

        userSellUseCaseInteractor.execute(sellInputData);
        final String priceMessage = getBookPrice(bookID);
        updatePriceInView(priceMessage);
    }

    /**
     * Fetch the book price using the book ID.
     * @param bookID the book ID
     * @return a string representing the price message (including currency)
     */
    private String getBookPrice(String bookID) {
        final SellBookDataFetcher priceFetcher = new SellBookDataFetcher();
        // return a string of the price with currency
        return priceFetcher.getBookPrice(bookID);
    }

    /**
     * Updates the SellView's price label with the fetched price.
     * @param priceMessage the price message to be displayed
     */
    private void updatePriceInView(String priceMessage) {
        sellView.updatePriceLabel(priceMessage);
    }
}
