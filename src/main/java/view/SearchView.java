package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import entity.listing.Listing;
import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.change_password.HomeState;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.update_listings.UpdateListingsController;
import interface_adapter.wishlist.add_to_wishlist.AddToWishlistController;
import interface_adapter.wishlist.remove_from_wishlist.RemoveFromWishlistController;

/**
 * The View for when the user is selling a book.
 */
public class SearchView extends JPanel implements PropertyChangeListener {

    private final String viewName = "search";
    private final SearchViewModel searchViewModel;
    private final HomeViewModel homeViewModel;
    private BackToHomeController backToHomeController;
    private SearchController searchController;

    private UpdateListingsController updateListingsController;
    private AddToWishlistController addToWishlistController;
    private RemoveFromWishlistController removeFromWishlistController;

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

    public SearchView(SearchViewModel searchViewModel, HomeViewModel homeViewModel) {

        // Integer constants
        final int twenty = 20;
        final int ten = 10;
        final int five = 5;
        final int four = 5;

        this.searchViewModel = searchViewModel;
        this.homeViewModel = homeViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        // Initialize the 'searchLabel' before adding it to the UI
        this.searchLabel = new JLabel("Search");

        final JLabel title = new JLabel("Search Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize all text boxes (JTextPanels)
        final LabelTextPanel searchByBookID = new LabelTextPanel(new JLabel("Book ID"), bookIDInputField);
        final LabelTextPanel searchByAuthors = new LabelTextPanel(new JLabel("Author"), authorsInputField);
        final LabelTextPanel searchByTitle = new LabelTextPanel(new JLabel("Title"), bookTitleInputField);
        final LabelTextPanel searchByPrice = new LabelTextPanel(new JLabel("Price"), priceInputField);

        // Username display
        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        final String[] searchColumnNames = {"Title", "Author(s)", "Price", "Book ID", "Rating", "Wishlist"};

        // Initial data for the table (empty)
        tableModel = new DefaultTableModel(searchColumnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == five;
            }

            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == five) {
                    return Boolean.class;
                }
                if (columnIndex == four) {
                    return Double.class;
                }
                return String.class;
            }
        };

        // Table to display filtered result
        filteredBookTable = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        filteredBookTable.setRowSorter(sorter);

        // Wishlist in last column
        final CheckboxCellEditor checkboxEditor = new CheckboxCellEditor();
        filteredBookTable.getColumnModel().getColumn(five).setCellEditor(checkboxEditor);

        // Add scroll pane for the table
        final JScrollPane tableScrollPane = new JScrollPane(filteredBookTable);

        // Layout
        final JPanel listings = new JPanel();
        listings.setLayout(new BorderLayout());
        listings.add(tableScrollPane, BorderLayout.CENTER);

        // Create searchLabel to display price
        searchLabel = new JLabel("Please input book ID, author(s), title, and/or price of book to search.");
        searchLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Rest of Search Screen Layout
        final JPanel topButtons = new JPanel();
        searchButton = new JButton("Generate New Search");
        topButtons.add(searchButton);

        final JPanel bottomButtons = new JPanel();
        back = new JButton("Back");
        bottomButtons.add(back);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set book ID to what the user types
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

        // Set authors to what the user types
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

        // Set title to what the user types
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

        // Set price to what the user types
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

        // Back button to HomeView
        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(back)) {
                        backToHomeController.execute();
                    }
                }
        );

        // Search button for search use case
        searchButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(searchButton)) {
                        final String userID = usernameInfo.getText();
                        final String bookID = bookIDInputField.getText();
                        final String authors = authorsInputField.getText();
                        final String bookTitle = bookTitleInputField.getText();
                        final String price = priceInputField.getText();

                        // Create error when user does not input anything
                        if ((bookID == null || bookID.isEmpty()) && (authors == null || authors.isEmpty())
                                && (bookTitle == null || bookTitle.isEmpty()) && (price == null || price.isEmpty())) {
                            searchController.message(SearchView.this, "Error, please input a valid book ID, "
                                    + "authors, and/or title of book to search.", "Error");
                            tableModel.setRowCount(0);
                        }
                        // Call search controller
                        else {
                            final String priceMessage = searchController.getSearchResults(
                                    userID, bookID, authors, bookTitle, price);

                            // After the search, the presenter will update the listings in the ViewModel
                            final SearchState searchState = searchViewModel.getState();
                            final List<Listing> matchingListings = searchState.getListings();

                            // Listings are null, so treat it as no results
                            if (matchingListings == null) {
                                JOptionPane.showMessageDialog(SearchView.this, "No matching listings found.",
                                        "Search Failed", JOptionPane.ERROR_MESSAGE);
                            }
                            // Else listings are found, update the table
                            else {
                                // Listings are found, update table
                                final int itemsFound = updateListingsTable(matchingListings);
                                createSearchMessage(priceMessage, itemsFound);
                            }
                        }
                    }
                }
        );

        // Wishlist implementation in the filtered table
        checkboxEditor.addActionListener(
                evt -> {
                    final int row = filteredBookTable.getEditingRow();
                    if (row != -1) {
                        final SearchState currentState = searchViewModel.getState();
                        final Boolean isChecked = (Boolean) filteredBookTable.getValueAt(row, 5);
                        final String bookId = (String) filteredBookTable.getValueAt(row, 3);

                        // Find the listing by bookId (instead of row)
                        Listing listingToModify = null;
                        for (Listing listing : currentState.getListings()) {
                            if (listing.getBook().getBookId().equals(bookId)) {
                                listingToModify = listing;
                                break;
                            }
                        }

                        // Update wishlist, if we found the listing
                        if (listingToModify != null) {
                            final String currentUsername = currentState.getUsername();
                            if (!isChecked) {
                                addToWishlistController.execute(currentUsername, listingToModify);
                            }
                            else {
                                removeFromWishlistController.execute(currentUsername, listingToModify);
                            }

                            // Refresh the table to reflect changes
                            tableModel.fireTableDataChanged();
                        }
                        // Note: This action listener is different from the HomeView's implementation because the row
                        // number changes after filtering the search. Using the 'getRow' method directly would cause
                        // errors due to this. As a result, checking the wishlist becomes more complex. This took
                        // significant debugging to resolve.
                    }
                }
        );

        // Add all relevant items to display
        this.add(Box.createVerticalStrut(twenty));
        this.add(title);
        this.add(Box.createVerticalStrut(twenty));
        this.add(usernameInfo);
        this.add(username);
        this.add(Box.createVerticalStrut(ten));

        this.add(topButtons);

        this.add(searchByBookID);
        this.add(searchByAuthors);
        this.add(searchByTitle);
        this.add(searchByPrice);

        this.add(listings);

        this.add(Box.createVerticalStrut(ten));
        this.add(searchLabel);
        this.add(Box.createVerticalStrut(ten));

        this.add(bottomButtons);
    }

    /**
     * Method that updates listings table with books that match search query.
     * @param listings the filtered listings to add to table after search has been generated
     * @return the number of books that match search query (for displaying user output message)
     */
    private int updateListingsTable(List<Listing> listings) {
        // Retrieve all current wishlist from home view
        final List<Listing> currentWishlist = homeViewModel.getState().getWishlist();
        // Clear table
        tableModel.setRowCount(0);
        // Update table with filtered listings only
        for (Listing listing : listings) {
            final boolean isInWishlist = currentWishlist.contains(listing);
            tableModel.addRow(new Object[]{
                    listing.getBook().getTitle(),
                    listing.getBook().getAuthors(),
                    listing.getPrice(),
                    listing.getBook().getBookId(),
                    listing.getBook().getRating(), isInWishlist});
        }
        // Number of books that match (for displaying user output message)
        return listings.size();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final SearchState state = (SearchState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("listings")) {
            final HomeState state = (HomeState) evt.getNewValue();
            updateListingsController.execute(state.getUsername());
        }
        if ("Search Results Updated".equals(evt.getPropertyName())) {
            final SearchState state = (SearchState) evt.getNewValue();
            updateListingsTable(state.getListings());
        }
        else if (evt.getPropertyName().equals("addedToWishlist")) {
            final SearchState state = (SearchState) evt.getNewValue();
            JOptionPane.showMessageDialog(SearchView.this, "Added to " + state.getUsername() + "'s wishlist!");
        }
        else if (evt.getPropertyName().equals("wishlistAddFail")) {
            JOptionPane.showMessageDialog(SearchView.this, "Failed to add to wishlist.");
        }
        else if (evt.getPropertyName().equals("removedFromWishlist")) {
            final SearchState state = (SearchState) evt.getNewValue();
            JOptionPane.showMessageDialog(SearchView.this, "Removed from " + state.getUsername() + "'s wishlist.");
        }
        else if (evt.getPropertyName().equals("wishlistRemoveFail")) {
            JOptionPane.showMessageDialog(SearchView.this, "Failed to remove from wishlist.");
        }
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }

    public String getViewName() {
        return viewName;
    }

    public void setUpdateListingsController(UpdateListingsController updateListingsController) {
        this.updateListingsController = updateListingsController;
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

    /**
     * Creates search message after generating search.
     * @param searchMessage a string of the search result
     * @param itemsFound the number of books found that fit the search query
     */
    public void createSearchMessage(String searchMessage, int itemsFound) {
        if (itemsFound == 0) {
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(SearchView.this, " We could not find any books that fit your"
                    + " search query.", "Search Failed", JOptionPane.ERROR_MESSAGE);
        }
        else if (itemsFound == 1) {
            JOptionPane.showMessageDialog(SearchView.this, searchMessage + " We were able to find " + itemsFound
                    + " book that fit your search query.", "Search Query", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(SearchView.this, searchMessage + " We were able to find " + itemsFound
                    + " books that fit your search query.", "Search Query", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
