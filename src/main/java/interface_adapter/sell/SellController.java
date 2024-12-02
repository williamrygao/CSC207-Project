package interface_adapter.sell;

import use_case.sell.SellInputBoundary;
import use_case.sell.SellInputData;

import javax.swing.*;
import java.awt.*;

/**
 * Controller for the Sell Use Case.
 */
public class SellController {
    private final SellInputBoundary userSellUseCaseInteractor;
    private String currentUsername;

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
        this.currentUsername = username;
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

    /**
     * Gets the current logged in username.
     * @return the username of the current logged-in user
     */
    public String getUsername() {
        return this.currentUsername;
    }

    /**
     * Executes the Sell Use Case for messages (errors or price display).
     * @param message the message to display to user
     * @param center where the panel will be centered
     * @param title the title for the panel
     */
    public void message(Component center, String message, String title) {
        if ("Error".equals(title)) {
            JOptionPane.showMessageDialog(center, message, title, JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(center, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
