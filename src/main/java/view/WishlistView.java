package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import entity.listing.Listing;
import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.wishlist.WishlistState;
import interface_adapter.wishlist.WishlistViewModel;
import interface_adapter.wishlist.add_to_wishlist.AddToWishlistController;
import interface_adapter.wishlist.remove_from_wishlist.RemoveFromWishlistController;

/**
 * The View for when the user is viewing their personal wishlist.
 */
public class WishlistView extends JPanel implements PropertyChangeListener {

    private final String viewName = "wishlist";
    private final WishlistViewModel wishlistViewModel;
    private BackToHomeController backToHomeController;
    private RemoveFromWishlistController removeFromWishlistController;
    private AddToWishlistController addToWishlistController;

    private final JLabel username;

    private final JButton back;

    private final JTable bookTable;
    private final DefaultTableModel tableModel;
    private final TableRowSorter<DefaultTableModel> sorter;

    public WishlistView(WishlistViewModel wishlistViewModel) {
        this.wishlistViewModel = wishlistViewModel;
        this.wishlistViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Wishlist Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        back = new JButton("Back");
        buttons.add(back);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(back)) {
                        backToHomeController.execute();
                    }
                }
        );

        checkboxEditor.addActionListener(
                evt -> {
                    final int row = bookTable.getEditingRow();
                    if (row != -1) {
                        final WishlistState currentState = wishlistViewModel.getState();
                        final Boolean isChecked = (Boolean) bookTable.getValueAt(row, 4);
                        tableModel.fireTableDataChanged();
                        final Listing listing = currentState.getWishlist().get(row);
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

        this.add(listings);

        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WishlistState state = (WishlistState) evt.getNewValue();
            username.setText(state.getUsername());
            updateTable(state.getWishlist());
        }
        else if (evt.getPropertyName().equals("addedToWishlist")) {
            final WishlistState state = (WishlistState) evt.getNewValue();
            JOptionPane.showMessageDialog(
                    null,
                    "Added to " + state.getUsername() + "'s wishlist!"
            );
        }
        else if (evt.getPropertyName().equals("wishlistAddFail")) {
            JOptionPane.showMessageDialog(null, "Failed to add to wishlist.");
        }
        else if (evt.getPropertyName().equals("removedFromWishlist")) {
            final WishlistState state = (WishlistState) evt.getNewValue();
            JOptionPane.showMessageDialog(
                    null, "Removed from " + state.getUsername() + "'s wishlist."
            );
        }
        else if (evt.getPropertyName().equals("wishlistRemoveFail")) {
            JOptionPane.showMessageDialog(null, "Failed to remove from wishlist.");
        }
    }

    private void updateTable(List<Listing> wishlist) {
        tableModel.setRowCount(0);
        for (Listing listing : wishlist) {
            final Object[] rowData = {
                    listing.getBook().getTitle(),
                    listing.getBook().getAuthors(),
                    listing.getPrice(),
                    listing.getBook().getRating(), true,
            };
            tableModel.addRow(rowData);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setBackToHomeController(BackToHomeController backToHomeController) {
        this.backToHomeController = backToHomeController;
    }

    public void setRemoveFromWishlistController(RemoveFromWishlistController removeFromWishlistController) {
        this.removeFromWishlistController = removeFromWishlistController;
    }

    public void setAddToWishlistController(AddToWishlistController addToWishlistController) {
        this.addToWishlistController = addToWishlistController;
    }
}
