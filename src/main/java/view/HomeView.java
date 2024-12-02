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
import interface_adapter.leave_rating.LeaveRatingController;
import interface_adapter.logout.LogoutController;
import interface_adapter.wishlist.remove_from_wishlist.RemoveFromWishlistController;
import interface_adapter.to_sell_view.ToSellController;
import interface_adapter.to_search_view.ToSearchController;
import interface_adapter.update_listings.UpdateListingsController;
import interface_adapter.wishlist.view_wishlist.ViewWishlistController;

/**
 * The home view for Joe Repka's Bookstore.
 */
public class HomeView extends JPanel implements PropertyChangeListener {
    private final String viewName = "home";
    private final HomeViewModel homeViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private ToSellController toSellController;
    private ToSearchController toSearchController;
    private ViewWishlistController viewWishlistController;
    private AddToWishlistController addToWishlistController;
    private RemoveFromWishlistController removeFromWishlistController;
    private UpdateListingsController updateListingsController;
    private LeaveRatingController leaveRatingController;

    private final JLabel username;
    private final JButton logOut;
    private final JButton toSell;
    private final JButton toSearch;
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
        final String[] columnNames = {"Title", "Author(s)", "Price", "Rating", "Wishlist"};

        // Initial data for the table (empty)
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }

            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) {
                    return Boolean.class;
                }
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

        final JScrollPane tableScrollPane = new JScrollPane(bookTable);

        final JPanel listings = new JPanel();
        listings.setLayout(new BorderLayout());
        listings.add(tableScrollPane, BorderLayout.CENTER);

        final JPanel topButtons = new JPanel();
        toSell = new JButton("Sell A Book");
        topButtons.add(toSell);

        toSearch = new JButton("Search for a Book");
        topButtons.add(toSearch);

        viewWishlist = new JButton("My Wishlist");
        topButtons.add(viewWishlist);

        toRate = new JButton("Rate a Book");
        topButtons.add(toRate);

        final JPanel bottomButtons = new JPanel();
        logOut = new JButton("Log Out");
        bottomButtons.add(logOut);

        changePassword = new JButton("Change Password");
        bottomButtons.add(changePassword);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
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
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final HomeState currentState = homeViewModel.getState();
                        final String oldPassword = currentState.getPassword();
                        final String newPassword = passwordInputField.getText();

                        if (newPassword == null || newPassword.isEmpty()) {
                            JOptionPane.showMessageDialog(HomeView.this, "Please enter a valid password", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else if (newPassword.equals(oldPassword)) {
                            JOptionPane.showMessageDialog(HomeView.this, "New password cannot be the same as the old "
                                    + "password. Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else if (newPassword.equals(currentState.getUsername())) {
                            JOptionPane.showMessageDialog(HomeView.this, "New password cannot be the same as your "
                                    + "username. Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            final int option = JOptionPane.showConfirmDialog(
                                    HomeView.this,
                                    "Are you sure you want to change your password?",
                                    "Password Change Conformation",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE
                            );
                            if (option == JOptionPane.YES_OPTION) {
                                this.changePasswordController.execute(
                                        currentState.getUsername(),
                                        newPassword);
                            }
                            // If NO_OPTION is selected, do nothing and stay on the current screen
                        }

                        currentState.setPassword(newPassword);
                        homeViewModel.setState(currentState);
                    }
                }
        );

        logOut.addActionListener(
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final HomeState currentState = homeViewModel.getState();
                        logoutController.execute(currentState.getUsername());
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

        toSearch.addActionListener(
                evt -> {
                    if (evt.getSource().equals(toSearch)) {
                        toSearchController.execute();
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

        toRate.addActionListener(
                evt -> {
                    // Action for rating a book
                    String bookId = JOptionPane.showInputDialog("Enter Book ID:");
                    String rating = JOptionPane.showInputDialog("Enter a rating (1 to 10):");

                    try {
                        int ratingValue = Integer.parseInt(rating);
                        if (ratingValue < 1 || ratingValue > 10) {
                            throw new NumberFormatException("Rating must be between 1 and 10.");
                        }

                        // Find the book by bookId and add the new rating
                        final HomeState currentState = homeViewModel.getState();
                        for (Listing listing : currentState.getListings()) {
                            if (listing.getBook().getBookId().equals(bookId)) {
                                // Add the new rating to the book's list of ratings
                                listing.getBook().addRating(ratingValue);
                            }
                        }

                        // After updating, refresh the table to reflect changes
                        updateTable(currentState.getListings(), currentState.getWishlist());
                    }
                    catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(HomeView.this,
                                "Invalid input. Please enter a number between 1 and 10.");
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

        this.add(Box.createVerticalStrut(20));
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
        }
        else if (evt.getPropertyName().equals("listings")) {
            final HomeState state = (HomeState) evt.getNewValue();
            updateListingsController.execute(state.getUsername());
        }
        else if (evt.getPropertyName().equals("updateTable")) {
            final HomeState state = (HomeState) evt.getNewValue();
            updateTable(state.getListings(), state.getWishlist());
        }
        else if (evt.getPropertyName().equals("password")) {
            final HomeState state = (HomeState) evt.getNewValue();
            JOptionPane.showMessageDialog(HomeView.this, "Password was successfully updated for " + state.getUsername() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (evt.getPropertyName().equals("wishlist")) {
            final HomeState state = (HomeState) evt.getNewValue();
            JOptionPane.showMessageDialog(HomeView.this, "Wishlist updated for " + state.getUsername());
        }
    }

    private void updateTable(List<Listing> listings, List<Listing> wishlist) {
        tableModel.setRowCount(0);
        for (Listing listing : listings) {
            double averageRating = listing.getBook().getAverageRating();
            final Object[] rowData = {
                    listing.getBook().getTitle(),
                    listing.getBook().getAuthors(),
                    listing.getPrice(),
                    listing.getBook().getAverageRating(),
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

    public void setToSearchController(ToSearchController toSearchController) {
        this.toSearchController = toSearchController;
    }

    public void setViewWishlistController(ViewWishlistController viewWishlistController) {
        this.viewWishlistController = viewWishlistController;
    }

    public void setRemoveFromWishlistController(RemoveFromWishlistController removeFromWishlistController) {
        this.removeFromWishlistController = removeFromWishlistController;
    }

    public void setUpdateListingsController(UpdateListingsController updateListingsController) {
        this.updateListingsController = updateListingsController;
    }

    public void setAddToWishlistController(AddToWishlistController addToWishlistController) {
        this.addToWishlistController = addToWishlistController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setToSellController(ToSellController toSellController) {
        this.toSellController = toSellController;
    }
}

