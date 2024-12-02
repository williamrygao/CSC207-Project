package view;

import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.filter_by_rating.FilterByRatingController;
import interface_adapter.filter_by_rating.FilterByRatingState;
import interface_adapter.filter_by_rating.FilterByRatingViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * The View for when the user is filtering listings by rating.
 */
public class FilterByRatingView extends JPanel implements PropertyChangeListener {
    private final String viewName = "filter by rating";
    private final FilterByRatingViewModel filterByRatingViewModel;
    private BackToHomeController backToHomeController;
    private FilterByRatingController filterByRatingController;

    private final JLabel username;
    private JLabel filterLabel;

    private final JTextField filterTextField = new JTextField(15);
    private final JButton filterButton;
    private final JButton backButton;

    public FilterByRatingView(FilterByRatingViewModel filterByRatingViewModel) {
        this.filterByRatingViewModel = filterByRatingViewModel;
        this.filterByRatingViewModel.addPropertyChangeListener(this);

        this.filterLabel = new JLabel("Filter by Rating");
        final JLabel title = new JLabel("Filter by Rating");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel filterTextPanel = new LabelTextPanel(new JLabel("Minimum Rating"), filterTextField);

        // text fields and buttons
        filterLabel = new JLabel("Please input a minimum rating from 1-10 to filter");
        filterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel topButtons = new JPanel();
        filterButton = new JButton("Filter");
        topButtons.add(filterButton);

        final JPanel bottomButtons = new JPanel();
        backButton = new JButton("Back");
        bottomButtons.add(backButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // the action methods for the text input field, filter button, and back to home button
        filterTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final FilterByRatingState currentState = filterByRatingViewModel.getState();
                currentState.setMinRating(Integer.parseInt(filterTextField.getText()));
                filterByRatingViewModel.setState(currentState);
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
                    final int minRating = filterByRatingViewModel.getState().getMinRating();
                    filterByRatingController.execute(minRating);
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

        this.add(title);
        this.add(filterTextPanel);
        this.add(filterLabel);
        this.add(filterTextField);
        this.add(filterButton);
        this.add(backButton);

    }

    public void setBackToHomeController(BackToHomeController backToHomeController) {
        this.backToHomeController = backToHomeController;
    }

    public void setFilterByRatingController(FilterByRatingController filterByRatingController) {
        this.filterByRatingController = filterByRatingController;
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("listings filtered")) {
            // go back to the home page after the listings have been filtered
            backToHomeController.execute();
        }
        if (evt.getPropertyName().equals("error")) {
            // if an error occurs
        }
    }
}
