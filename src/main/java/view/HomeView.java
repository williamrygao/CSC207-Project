package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import entity.Listing;
import interface_adapter.wishlist.add_to_wishlist.AddToWishlistController;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.wishlist.remove_from_wishlist.RemoveFromWishlistController;
import interface_adapter.to_sell_view.ToSellController;
import interface_adapter.update_listings.UpdateListingsController;
import interface_adapter.wishlist.view_wishlist.ViewWishlistController;

/**
 * The View for when the user is logged into the program.
 */
public class HomeView extends JPanel implements PropertyChangeListener {
    private final String viewName = "home";
    private final HomeViewModel homeViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private ToSellController toSellController;
    private ViewWishlistController viewWishlistController;
    private AddToWishlistController addToWishlistController;
    private RemoveFromWishlistController removeFromWishlistController;
    private UpdateListingsController updateListingsController;

    private final JLabel username;

    private final JButton logOut;
    private final JButton toSell;
    private final JButton viewWishlist;
    private final JButton toRate;

    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;

    private final JTable bookTable;
    private final DefaultTableModel tableModel;
    private final TableRowSorter<DefaultTableModel> sorter;

    public HomeView(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Home Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Table column names
        final String[] columnNames = {"Title", "Author", "Price", "Rating", "Wishlist"};

        // Initial data for the table (empty)
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }

            public Class<?> getColumnClass(int columnIndex) {
                // Set the column type to Boolean for the "Wishlist" column
                if (columnIndex == 4) {
                    return Boolean.class;
                }
                // Other columns are String or Double, as before
                if (columnIndex == 3) {
                    return Double.class;
                }
                return String.class;
            }
        };

        bookTable = new JTable(tableModel);

        sorter = new TableRowSorter<>(tableModel);
        bookTable.setRowSorter(sorter);

        final CheckboxCellEditor checkboxEditor = new CheckboxCellEditor();
        bookTable.getColumnModel().getColumn(4).setCellEditor(checkboxEditor);

        // Add scroll pane for the table
        final JScrollPane tableScrollPane = new JScrollPane(bookTable);

        final JPanel listings = new JPanel();
        listings.setLayout(new BorderLayout());
        listings.add(tableScrollPane, BorderLayout.CENTER);

        final JPanel topButtons = new JPanel();
        toSell = new JButton("Sell A Book");
        topButtons.add(toSell);

        viewWishlist = new JButton("My Wishlist");
        topButtons.add(viewWishlist);

        toRate = new JButton("My Rating");
        topButtons.add(toRate);

        final JPanel bottomButtons = new JPanel();
        logOut = new JButton("Log Out");
        bottomButtons.add(logOut);

        changePassword = new JButton("Change Password");
        bottomButtons.add(changePassword);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final HomeState currentState = homeViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                homeViewModel.setState(currentState);
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

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final HomeState currentState = homeViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        logOut.addActionListener(
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final HomeState currentState = homeViewModel.getState();
                        currentState.getUsername();
                        logoutController.execute(
                                currentState.getUsername()
                        );
                    }
                }
        );

        toSell.addActionListener(
                evt -> {
                    if (evt.getSource().equals(toSell)) {
                        toSellController.execute();
                    }
                }
        );

        viewWishlist.addActionListener(
                evt -> {
                    if (evt.getSource().equals(viewWishlist)) {
                        final HomeState currentState = homeViewModel.getState();
                        viewWishlistController.execute(currentState.getUsername());
                    }
                }
        );

        checkboxEditor.addActionListener(
                evt -> {
                final int row = bookTable.getEditingRow();
                if (row != -1) {
                    final HomeState currentState = homeViewModel.getState();
                    final Boolean isChecked = (Boolean) bookTable.getValueAt(row, 4);
                    tableModel.fireTableDataChanged();
                    final Listing listing = currentState.getListings().get(row);
                    final String currentUsername = currentState.getUsername();
                    if (!isChecked) {
                        // Call your controller's method to add to wishlist
                        addToWishlistController.execute(currentUsername, listing);
                    }
                    else {
                        // Call your controller's method to remove from wishlist
                        removeFromWishlistController.execute(currentUsername, listing);
                    }
                }
            }
        );

        this.add(title);
        this.add(Box.createVerticalStrut(20));
        this.add(usernameInfo);
        this.add(username);
        this.add(Box.createVerticalStrut(20));

        this.add(topButtons);

        this.add(listings);

        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(bottomButtons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final HomeState state = (HomeState) evt.getNewValue();
            username.setText(state.getUsername());
            updateListingsController.execute(state.getUsername());
        }
        else if (evt.getPropertyName().equals("updateTable")) {
            final HomeState state = (HomeState) evt.getNewValue();
            updateTable(state.getListings(), state.getWishlist());
        }
        else if (evt.getPropertyName().equals("password")) {
            final HomeState state = (HomeState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }
        else if (evt.getPropertyName().equals("wishlist")) {
            final HomeState state = (HomeState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "wishlist updated for " + state.getUsername());
        }
    }

    private void updateTable(List<Listing> listings, List<Listing> wishlist) {
        tableModel.setRowCount(0);
        for (Listing listing : listings) {
            final Object[] rowData = {
                    listing.getBook().getTitle(),
                    listing.getBook().getAuthors(),
                    listing.getPrice(),
                    listing.getBook().getRating(),
                    wishlist.contains(listing),
            };
            tableModel.addRow(rowData);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setToSellController(ToSellController toSellController) {
        this.toSellController = toSellController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setViewWishlistController(ViewWishlistController viewWishlistController) {
        this.viewWishlistController = viewWishlistController;
    }

    public void setAddToWishlistController(AddToWishlistController addToWishlistController) {
        this.addToWishlistController = addToWishlistController;
    }

    public void setRemoveFromWishlistController(RemoveFromWishlistController removeFromWishlistController) {
        this.removeFromWishlistController = removeFromWishlistController;
    }

    public void setUpdateListingsController(UpdateListingsController updateListingsController) {
        this.updateListingsController = updateListingsController;
    }
}
