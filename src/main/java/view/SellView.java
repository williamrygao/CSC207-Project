package view;

import entity.Book;
import entity.BookFactory;
import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.sell.SellController;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;
import use_case.sell.SellBookDataFetcher;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The View for when the user is selling a book.
 */
public class SellView extends JPanel implements PropertyChangeListener {

    private final String viewName = "sell";
    private final SellViewModel sellViewModel;
    private BackToHomeController backToHomeController;
    private SellController sellController;

    private final JLabel username;
    private JLabel priceLabel;
    private JTextArea sellTextArea;

    private final JTextField bookIDInputField = new JTextField(15);
    private final JTextField listPrice = new JTextField(15);
    private final JButton price;
    private final JButton back;
    private final JButton sell;

    private final SellBookDataFetcher sellBookDataFetcher;

    public SellView(SellViewModel sellViewModel) {
        this.sellViewModel = sellViewModel;
        this.sellViewModel.addPropertyChangeListener(this);
        this.sellBookDataFetcher = new SellBookDataFetcher();

        // Initialize the 'priceLabel' before adding it to the UI
        this.priceLabel = new JLabel("Price: N/A");

        // Initialize the 'sellTextArea' before adding it to the UI
        this.sellTextArea = new JTextArea(4, 20);
        sellTextArea.setEditable(false);
        sellTextArea.setWrapStyleWord(true);
        sellTextArea.setLineWrap(true);
        sellTextArea.setText("Market price and listing details will appear here if available.");

        final JLabel title = new JLabel("Sell Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel bookInfo = new LabelTextPanel(
                new JLabel("Book ID"), bookIDInputField);

        final LabelTextPanel priceListing = new LabelTextPanel(
                new JLabel("List Price"), listPrice);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create priceLabel to display price
        priceLabel = new JLabel("Market Price will be displayed here if availiable.");
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        back = new JButton("Back");
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttons.add(back);

        price = new JButton("List Market Price");
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
                        final String priceMessage = sellBookDataFetcher.getBookPrice(bookID);
                        updatePriceLabel(priceMessage);
                    }
                }
        );

        sell.addActionListener(
                // This creates an anonymous subclass of ActionListener and
                // instantiates it.
                evt -> {
                    if (evt.getSource().equals(sell)) {
                        final String sellingPrice = listPrice.getText();
                        final String bookID = bookIDInputField.getText();
                        final String userID = username.getText();

                        final String sellMessage = sellBookDataFetcher.getUserSellingListing(sellingPrice, bookID, userID);
                        updateSellTextArea(sellMessage);

                        final Book book = BookFactory.createBook(bookID);
                        final String bookTitle = book.getTitle();
                        final List<String> authors = book.getAuthors();

                        // Change HomeView listing of book
                        final Object[] newRow = {bookTitle, authors, sellingPrice, "No Rating" };

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(20));

        // Title and labels
        this.add(title);
        this.add(Box.createVerticalStrut(20));
        this.add(usernameInfo);
        this.add(username);
        this.add(Box.createVerticalStrut(10));

        // Book info panels
        this.add(bookInfo);
        this.add(priceListing);
        this.add(Box.createVerticalStrut(10));

        // Display price and sell label
        this.add(priceLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(sellTextArea);

        // Add JPanel to wrap the sellTextArea and add padding
        final JPanel sellTextAreaPanel = new JPanel();
        sellTextAreaPanel.setLayout(new BoxLayout(sellTextAreaPanel, BoxLayout.X_AXIS));
        sellTextAreaPanel.add(Box.createHorizontalStrut(40));
        final JScrollPane scrollPane = new JScrollPane(sellTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        sellTextAreaPanel.add(new JScrollPane(sellTextArea));
        sellTextAreaPanel.add(Box.createHorizontalStrut(40));
        this.add(sellTextAreaPanel);

        // Buttons panel
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final SellState state = (SellState) evt.getNewValue();
            username.setText(state.getUsername());
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
     * Updates the price label with the fetched price.
     * @param priceMessage a string of the fetched price
     */
    public void updatePriceLabel(String priceMessage) {
        priceLabel.setText(priceMessage);
    }

    /**
     * Updates the label with the fetched price.
     * @param sellMessage a string of the fetched price
     */
    public void updateSellTextArea(String sellMessage) {
        sellTextArea.setText(sellMessage);
    }
}
