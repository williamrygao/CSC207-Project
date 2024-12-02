package view;

import entity.listing.Listing;
import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.filter_by_price.FilterByPriceController;
import interface_adapter.filter_by_price.FilterByPriceState;
import interface_adapter.filter_by_price.FilterByPriceViewModel;
import interface_adapter.search.SearchState;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


/**
 * The View for when the user is filtering listings by price.
 */
public class FilterByPriceView extends JPanel implements PropertyChangeListener {
    private final String viewName = "filter by price";
    private final FilterByPriceViewModel filterByPriceViewModel;
    private BackToHomeController backToHomeController;
    private FilterByPriceController filterByPriceController;

    private final JTextField filterTextField = new JTextField(5);
    private final JButton filterButton;
    private final JButton backButton;

    private final JTable filteredBookTable;
    private JTable listingsTable;
    private final DefaultTableModel tableModel;
    private final TableRowSorter<DefaultTableModel> sorter;

    public FilterByPriceView(FilterByPriceViewModel filterByPriceViewModel) {
        this.filterByPriceViewModel = filterByPriceViewModel;
        this.filterByPriceViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Filter by Price");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel filterTextPanel = new LabelTextPanel(
                new JLabel("Please input a maximum price to filter"), filterTextField);

        filterButton = new JButton("Filter");
        backButton = new JButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final String[] searchColumnNames = {"Title", "Author(s)", "Price", "BookID", "Rating"};

        // Initial data for the table (empty)
        tableModel = new DefaultTableModel(searchColumnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }

            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) {
                    return Double.class;
                }
                return String.class;
            }
        };

        filteredBookTable = new JTable(tableModel);

        listingsTable = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        filteredBookTable.setRowSorter(sorter);

        // Add scroll pane for the table
        final JScrollPane tableScrollPane = new JScrollPane(filteredBookTable);

        final JPanel listings = new JPanel();
        listings.setLayout(new BorderLayout());
        listings.add(tableScrollPane, BorderLayout.CENTER);

        // the action methods for the text input field, filter button, and back to home button
        filterTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final String text = filterTextField.getText();
                if (isNumeric(text)) {
                    final FilterByPriceState currentState = filterByPriceViewModel.getState();
                    currentState.setMaxPrice(Integer.parseInt(text));
                    filterByPriceViewModel.setState(currentState);
                }
                else {
                    filterByPriceViewModel.getState().setMaxPrice(0);
                }
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

        filterButton.addActionListener(
            evt -> {
                if (evt.getSource().equals(filterButton)) {
                    if (isNumeric(filterTextField.getText())) {
                        final int maxPrice = filterByPriceViewModel.getState().getMaxPrice();
                        filterByPriceController.execute(maxPrice);
                    }
                    else {
                        filterByPriceController.error(FilterByPriceView.this,
                                "Please input an integer value.");
                    }
                }
            }
        );

        backButton.addActionListener(
            evt -> {
                if (evt.getSource().equals(backButton)) {
                    backToHomeController.execute();
                }
            }
        );

        this.add(Box.createVerticalStrut(20));
        this.add(title);
        this.add(Box.createVerticalStrut(50));
        this.add(filterTextPanel);
        this.add(listings);
        this.add(filterButton);
        this.add(backButton);
        this.add(Box.createVerticalStrut(50));

    }

    public void setBackToHomeController(BackToHomeController backToHomeController) {
        this.backToHomeController = backToHomeController;
    }

    public void setFilterByPriceController(FilterByPriceController filterByPriceController) {
        this.filterByPriceController = filterByPriceController;
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("listings filtered")) {
            updateListingsTable();
        }
        if (evt.getPropertyName().equals("error")) {
            // if an error occurs
        }
    }

    private void updateListingsTable() {
        final List<Listing> listings = filterByPriceViewModel.getState().getListings();

        tableModel.setRowCount(0);

        // Add rows to the table
        for (Listing listing : listings) {
            tableModel.addRow(new Object[] {
                    listing.getBook().getTitle(),
                    listing.getBook().getAuthors(),
                    listing.getPrice(),
                    listing.getBook().getBookId(),
                    listing.getBook().getRating()});
        }
    }

    /**
     * Checks whether a string is numeric.
     * @param str the input string
     * @return true if the string can be converted to an integer, false otherwise
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
