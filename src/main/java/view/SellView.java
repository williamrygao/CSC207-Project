package view;

import entity.book.Book;
import entity.book.BookFactory;
import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.sell.SellController;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is selling a book.
 */
public class SellView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Sell";
    private final SellViewModel sellViewModel;
    private BackToHomeController backToHomeController;
    private SellController sellController;

    private final JLabel username;
    private JLabel priceLabel;

    private final JTextField bookIDInputField = new JTextField(15);
    private final JTextField priceInputField = new JTextField(15);
    private final JButton price;
    private final JButton back;
    private final JButton sell;

    public SellView(SellViewModel sellViewModel) {
        this.sellViewModel = sellViewModel;
        this.sellViewModel.addPropertyChangeListener(this);

        // Initialize the 'priceLabel' before adding it to the UI
        this.priceLabel = new JLabel("Price: N/A");

        final JLabel title = new JLabel("Sell Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel bookInfo = new LabelTextPanel(new JLabel("Book ID"), bookIDInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create priceLabel to display price
        priceLabel = new JLabel("Retail price will be displayed if available.");
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel priceInfo = new LabelTextPanel(new JLabel("Your Price"), priceInputField);

        final JPanel buttons = new JPanel();
        back = new JButton("Back");
        buttons.add(back);

        price = new JButton("List Retail Price");
        buttons.add(price);

        sell = new JButton("Sell");
        buttons.add(sell);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        bookIDInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SellState currentState = sellViewModel.getState();
                currentState.setBookID(bookIDInputField.getText());
                sellViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        priceInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SellState currentState = sellViewModel.getState();
                currentState.setPrice(String.valueOf(priceInputField.getText()));
                sellViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(back)) {
                        backToHomeController.execute();
                    }
                }
        );

        price.addActionListener(
                // This creates an anonymous subclass of ActionListener and
                // instantiates it.
                evt -> {
                    if (evt.getSource().equals(price)) {
                        final String bookID = bookIDInputField.getText();
                        if (bookID == null || bookID.isEmpty()) {
                            sellController.message(SellView.this, "Error, please input a valid book ID", "Error");
                        }
                        else {
                            final String priceMessage = sellController.getBookPrice(bookID);
                            sellController.message(SellView.this, priceMessage, "Retail Price");
                        }
                    }
                }
        );

        sell.addActionListener(
                // This creates an anonymous subclass of ActionListener and
                // instantiates it.
                evt -> {
                    if (evt.getSource().equals(sell)) {
                        final SellState currentState = sellViewModel.getState();
                        final String bookID = bookIDInputField.getText();
                        final String sellingPrice = priceInputField.getText();
                        if (bookID == null || bookID.isEmpty()) {
                            sellController.message(SellView.this, "Error, please input a valid book ID", "Error");
                        }
                        else if (sellingPrice == null || sellingPrice.isEmpty()) {
                            sellController.message(SellView.this, "Error, please input a valid selling price", "Error");
                        }
                        else {
                            sellController.execute(currentState.getUsername(), currentState.getPassword(),
                                    currentState.getBookID(), currentState.getPrice());
                        }
                    }
                }
        );

        this.add(Box.createVerticalStrut(20));
        this.add(title);
        this.add(Box.createVerticalStrut(20));
        this.add(usernameInfo);
        this.add(username);
        this.add(Box.createVerticalStrut(10));

        this.add(bookInfo);
        this.add(priceInfo);
        this.add(Box.createVerticalStrut(10));
        this.add(priceLabel);
        this.add(Box.createVerticalStrut(10));

        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final SellState state = (SellState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("not sold")) {
            final SellState state = (SellState) evt.getNewValue();
            JOptionPane.showMessageDialog(SellView.this, state.getSellError());
        }
        else if (evt.getPropertyName().equals("listed for sale")) {
            final SellState state = (SellState) evt.getNewValue();
            JOptionPane.showMessageDialog(SellView.this, createSellMessage(priceInputField.getText(),
                    bookIDInputField.getText(), state.getUsername()));
        }
    }

    public void setSellController(SellController sellController) {
        this.sellController = sellController;
    }

    public String getViewName() {
        return viewName;
    }

    public void setBackToHomeController(BackToHomeController backToHomeController) {
        this.backToHomeController = backToHomeController;
    }

    /**
     * Method to create sell message.
     * @param SellingPrice the user's selling price
     * @param bookID the book ID
     * @param userID the user's identification: username
     * @return a message as a string for the user
     */
    public String createSellMessage(String SellingPrice, String bookID, String userID) {
        final BookFactory bookFactory = new BookFactory();
        final Book book = bookFactory.createBook(bookID);
        final String title = book.getTitle();
        final String authors = book.getAuthors();
        String formattedAuthors = "";
        String returnMessage = "";

        final String[] authorArray = authors.split(",\\s*");

        if (authorArray.length > 2) {
            formattedAuthors = authorArray[0] + ", " + authorArray[1] + ", et al.";
        }
        else if (authorArray.length == 2) {
            formattedAuthors = authorArray[0] + ", and " + authorArray[1];
        }
        else {
            formattedAuthors = authors;
        }

        if (SellingPrice == null || SellingPrice.isEmpty()) {
            returnMessage = "Error, please enter a valid price to list your book at on Book Marketplace.";
        }
        else {
            returnMessage = "Hello " + userID + ", \nThank you for listing your book with us! We've"
                    + " successfully updated your listing for the book titled: " + "'" + title + "'"
                    + " by " + formattedAuthors + " with your preferred price of: " + SellingPrice + "."
                    + " \nOther users can now view your book at this price. You will be notified if"
                    + " anyone expresses interest or makes a purchase. If you'd like to edit your listing"
                    + " at any time, you can do so through your account.\nIf you have any questions or"
                    + " need assistance, don't hesitate to reach out. We're here to help\nBest regards";
        }

        return returnMessage;

    }
}
