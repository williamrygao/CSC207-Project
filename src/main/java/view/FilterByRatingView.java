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

    private final JTextField filterTextField = new JTextField(5);
    private final JButton filterButton;
    private final JButton backButton;

    public FilterByRatingView(FilterByRatingViewModel filterByRatingViewModel) {
        this.filterByRatingViewModel = filterByRatingViewModel;
        this.filterByRatingViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Filter by Rating");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel filterTextPanel = new LabelTextPanel(
                new JLabel("Please input a minimum rating from 1-10 to filter"), filterTextField);

        filterButton = new JButton("Filter");
        backButton = new JButton("Back");

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
