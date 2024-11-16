package view;

import interface_adapter.back_to_logged_in.BackToLoggedInController;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.sell.SellController;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is selling a book.
 */
public class SellView extends JPanel implements PropertyChangeListener {

    private final String viewName = "sell";
    private final SellViewModel sellViewModel;
//    private final JLabel passwordErrorField = new JLabel();
    private BackToLoggedInController backToLoggedInController;
    private SellController sellController;

    private final JLabel username;

    private final JTextField bookIDInputField = new JTextField(15);
    private final JButton sell;

    private final JButton back;

    public SellView(SellViewModel sellViewModel) {
        this.sellViewModel = sellViewModel;
        this.sellViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Sell Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel bookInfo = new LabelTextPanel(
                new JLabel("Book ID"), bookIDInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        back = new JButton("Back");
        buttons.add(back);

        sell = new JButton("Sell");
        buttons.add(sell);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        bookIDInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SellState currentState = sellViewModel.getState();
                currentState.setBook(bookIDInputField.getText());
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
                        final SellState currentState = sellViewModel.getState();

                        this.backToLoggedInController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
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
                                currentState.getUsername(), currentState.getPassword(), currentState.getBook()
                        );
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.add(bookInfo);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
