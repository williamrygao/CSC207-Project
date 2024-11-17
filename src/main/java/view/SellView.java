package view;

import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.change_password.HomeState;
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

    private final String viewName = "sell";
    private final SellViewModel sellViewModel;
    private BackToHomeController backToHomeController;
    private SellController sellController;

    private final JLabel username;
    private JLabel priceLabel;

    private final JTextField bookIDInputField = new JTextField(15);
    private final JButton sell;
    private final JButton back;

    public SellView(SellViewModel sellViewModel) {
        this.sellViewModel = sellViewModel;
        this.sellViewModel.addPropertyChangeListener(this);

        // Initialize the 'priceLabel' before adding it to the UI
        this.priceLabel = new JLabel("Price: N/A");

        final JLabel title = new JLabel("Sell Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel bookInfo = new LabelTextPanel(
                new JLabel("Book ID"), bookIDInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        // Create priceLabel to display price
        priceLabel = new JLabel("Price will be displayed here.");
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        back = new JButton("Back");
        buttons.add(back);

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

        sell.addActionListener(
                // This creates an anonymous subclass of ActionListener and
                // instantiates it.
                evt -> {
                    if (evt.getSource().equals(sell)) {
                        final SellState currentState = sellViewModel.getState();
                        sellController.execute(
                                currentState.getUsername(), currentState.getPassword(), currentState.getBookID()
                        );
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.add(bookInfo);
        this.add(priceLabel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final SellState state = (SellState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("listed for sale")) {
            final SellState state = (SellState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, state.getBookID() + "has been listed for sale.");
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
}
