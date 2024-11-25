package view;

import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.remove_from_wishlist.WishlistState;
import interface_adapter.remove_from_wishlist.RemoveFromWishlistController;
import interface_adapter.remove_from_wishlist.WishlistViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

        String[] columnNames = {"Title", "Author", "Price", "Rating"};

        // Initial data for the table (empty)
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                // Return false to make all cells non-editable
                return false;
            }

            public Class<?> getColumnClass(int columnIndex) {
                // Specify column data types to allow proper sorting
                if (columnIndex == 2) {
                    return Double.class;
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

        // Add scroll pane for the table
        JScrollPane tableScrollPane = new JScrollPane(bookTable);

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
        }
    }

    public void setRemoveFromWishlistController(RemoveFromWishlistController removeFromWishlistController) {
        this.removeFromWishlistController = removeFromWishlistController;
    }

    public String getViewName() {
        return viewName;
    }

    public void setBackToHomeController(BackToHomeController backToHomeController) {
        this.backToHomeController = backToHomeController;
    }
}
