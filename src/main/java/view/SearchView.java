package view;

import entity.Listing;
import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

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
 * The View for when the user is selling a book.
 */
public class SearchView extends JPanel implements PropertyChangeListener {

    private final String viewName = "search";
    private final SearchViewModel searchViewModel;
    private JTable listingsTable;
    private BackToHomeController backToHomeController;
    private SearchController searchController;

    private final JLabel username;
    private JLabel searchLabel;

    private final JTextField bookIDInputField = new JTextField(15);
    private final JTextField authorsInputField = new JTextField(15);
    private final JTextField bookTitleInputField = new JTextField(15);
    private final JTextField priceInputField = new JTextField(15);

    private final JButton searchButton;
    private final JButton back;

    private final JTable filteredBookTable;
    private final DefaultTableModel tableModel;
    private final TableRowSorter<DefaultTableModel> sorter;

    public SearchView(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        // Initialize the 'searchLabel' before adding it to the UI
        this.searchLabel = new JLabel("Search");

        final JLabel title = new JLabel("Search Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel searchByBookID = new LabelTextPanel(new JLabel("Book ID"), bookIDInputField);
        final LabelTextPanel searchByAuthors = new LabelTextPanel(new JLabel("Author"), authorsInputField);
        final LabelTextPanel searchByTitle = new LabelTextPanel(new JLabel("Title"), bookTitleInputField);
        final LabelTextPanel searchByPrice = new LabelTextPanel(new JLabel("Price"), priceInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        final String[] searchColumnNames = {"Title", "Author(s)", "Price", "BookID", "Rating", "Wishlist"};

        // Initial data for the table (empty)
        tableModel = new DefaultTableModel(searchColumnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 5;
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

        // Create searchLabel to display price
        searchLabel = new JLabel("Please input book ID, author(s), title, and/or price of book to search");
        searchLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel topButtons = new JPanel();
        searchButton = new JButton("Generate Search");
        topButtons.add(searchButton);

        final JPanel bottomButtons = new JPanel();
        back = new JButton("Back");
        bottomButtons.add(back);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        bookIDInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SearchState currentState = searchViewModel.getState();
                currentState.setBookID(bookIDInputField.getText());
                searchViewModel.setState(currentState);
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

        authorsInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SearchState currentState = searchViewModel.getState();
                currentState.setAuthors(String.valueOf(authorsInputField.getText()));
                searchViewModel.setState(currentState);
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

        bookTitleInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SearchState currentState = searchViewModel.getState();
                currentState.setTitle(String.valueOf(bookTitleInputField.getText()));
                searchViewModel.setState(currentState);
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
                final SearchState currentState = searchViewModel.getState();
                currentState.setPrice(String.valueOf(priceInputField.getText()));
                searchViewModel.setState(currentState);
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
                        updateSearchLabel("Please input book ID, author(s), title, and/or price of book to search");
                    }
                }
        );

        searchButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and
                // instantiates it.
                evt -> {
                    if (evt.getSource().equals(searchButton)) {
                        final String userID = usernameInfo.getText();
                        final String bookID = bookIDInputField.getText();
                        final String authors = authorsInputField.getText();
                        final String bookTitle = bookTitleInputField.getText();
                        final String price = priceInputField.getText();

                        if ((bookID == null || bookID.isEmpty()) && (authors == null || authors.isEmpty())
                                && (bookTitle == null || bookTitle.isEmpty()) && (price == null || price.isEmpty())) {
                            searchController.error(SearchView.this, "Error, please input a valid book ID, "
                                    + "authors, and/or title of book to search");
                        }
                        else {
                            final String priceMessage = searchController.getSearchResults(
                                    userID, bookID, authors, bookTitle, price);
                            updateSearchLabel(priceMessage);
                        }
                    }
                }
        );

        this.add(title);
        this.add(Box.createVerticalStrut(20));
        this.add(usernameInfo);
        this.add(username);
        this.add(Box.createVerticalStrut(10));

        this.add(topButtons);

        this.add(searchByBookID);
        this.add(searchByAuthors);
        this.add(searchByTitle);
        this.add(searchByPrice);

        this.add(listings);

        this.add(Box.createVerticalStrut(10));
        this.add(searchLabel);
        this.add(Box.createVerticalStrut(10));

        this.add(bottomButtons);
    }

    private void updateListingsTable() {
        List<Listing> listings = searchViewModel.getState().getListings();

        tableModel.setRowCount(0);

        // Add rows to the table
        for (Listing listing : listings) {
            tableModel.addRow(new Object[] {
                    listing.getBook().getTitle(),
                    listing.getBook().getAuthors(),
                    listing.getPrice(),
                    listing.getBook().getBookId(),
                    listing.getBook().getRating(), "Add to Wishlist"});
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Search Results Updated")) {
            final SearchState state = (SearchState) evt.getNewValue();
            username.setText(state.getUsername());
            updateListingsTable();
        }
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }

    public String getViewName() {
        return viewName;
    }

    public void setBackToHomeController(BackToHomeController backToHomeController) {
        this.backToHomeController = backToHomeController;
    }

    /**
     * Updates the search label after generating search.
     * @param searchMessage a string of the search result
     */
    public void updateSearchLabel(String searchMessage) {
        searchLabel.setText(searchMessage);
    }
}
