package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import entity.Listing;
import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.remove_from_wishlist.RemoveFromWishlistController;
import interface_adapter.remove_from_wishlist.WishlistState;
import interface_adapter.remove_from_wishlist.WishlistViewModel;

/**
 * The View for when the user is viewing their personal wishlist.
 */
public class WishlistView extends JPanel implements PropertyChangeListener {

    private final String viewName = "wishlist";
    private final WishlistViewModel wishlistViewModel;
    private BackToHomeController backToHomeController;
    private RemoveFromWishlistController removeFromWishlistController;

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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final String[] columnNames = {"Title", "Author", "Price", "Rating", "Wishlist"};

        // Initial data for the table (empty)
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                // Return false to make all cells non-editable
                return false;
            }

            public Class<?> getColumnClass(int columnIndex) {
                // Specify column data types to allow proper sorting
                if (columnIndex == 3) {
                    return Double.class;
                }
                if (columnIndex == 4) {
                    return JButton.class;
                }
                return String.class;
            }
        };

        bookTable = new JTable(tableModel);

        sorter = new TableRowSorter<>(tableModel);
        bookTable.setRowSorter(sorter);

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

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.add(listings);

        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WishlistState state = (WishlistState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("wishlist")) {
            final WishlistState state = (WishlistState) evt.getNewValue();
            updateTable(state.getWishlist());
        }
    }

    private void updateTable(List<Listing> newListings) {
        tableModel.setRowCount(0);
        for (Listing newListing : newListings) {
            final Object[] rowData = {
                    newListing.getBook().getTitle(),
                    newListing.getBook().getAuthors(),
                    newListing.getPrice(),
                    newListing.getBook().getRating(),
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
}
