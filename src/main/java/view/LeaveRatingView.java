package view;

import entity.listing.Listing;
import interface_adapter.leave_rating.LeaveRatingController;
import interface_adapter.leave_rating.LeaveRatingState;
import interface_adapter.leave_rating.LeaveRatingViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is leaving a rating for a book.
 */
public class LeaveRatingView extends JPanel implements PropertyChangeListener {

    private final String viewName = "rate";
    private final LeaveRatingViewModel leaveRatingViewModel;
    private LeaveRatingController leaveRatingController;

    private final JLabel username;
    private final JTextField bookIDInputField = new JTextField(15);
    private final JTextField ratingInputField = new JTextField(15);
    private final JButton back;
    private final JButton submitRating;
    private final JLabel feedbackLabel;

    public LeaveRatingView(LeaveRatingViewModel leaveRatingViewModel) {
        this.leaveRatingViewModel = leaveRatingViewModel;
        this.leaveRatingViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Rate Book Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel bookInfo = new LabelTextPanel(
                new JLabel("Book ID"), bookIDInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel ratingInfo = new LabelTextPanel(new JLabel("Your Rating (1-10)"), ratingInputField);

        final JPanel buttons = new JPanel();
        back = new JButton("Back");
        buttons.add(back);

        submitRating = new JButton("Submit Rating");
        buttons.add(submitRating);

        feedbackLabel = new JLabel(" ");
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        bookIDInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LeaveRatingState currentState = leaveRatingViewModel.getState();
                currentState.setBookID(bookIDInputField.getText());
                leaveRatingViewModel.setState(currentState);
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

        ratingInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LeaveRatingState currentState = leaveRatingViewModel.getState();
                try {
                    currentState.setNewRating(Double.parseDouble(ratingInputField.getText()));
                    leaveRatingViewModel.setState(currentState);
                } catch (NumberFormatException e) {
                    feedbackLabel.setText("Please enter a valid numeric rating.");
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

        back.addActionListener(evt -> {
            if (evt.getSource().equals(back)) {
                // Handle back to home (this could be another controller, like the home screen)
            }
        });

        submitRating.addActionListener(evt -> {
            if (evt.getSource().equals(submitRating)) {
                final LeaveRatingState currentState = leaveRatingViewModel.getState();
                String bookID = currentState.getBookID();
                String ratingStr = String.valueOf(currentState.getNewRating());

                if (bookID == null || bookID.isEmpty() || ratingStr == null || ratingStr.isEmpty()) {
                    feedbackLabel.setText("Error: Please fill in both book ID and rating.");
                    return;
                }

                try {
                    int rating = Integer.parseInt(ratingStr);
                    if (rating < 1 || rating > 10) {
                        feedbackLabel.setText("Error: Rating must be between 1 and 10.");
                    } else {
                        // Example for username, password, and listing retrieval
                        String username = currentState.getUsername();
                        String password = "user-password"; // Replace with actual logic to get password
                        Listing listing = fetchListingByBookID(bookID); // Replace with actual method to get Listing

                        if (listing != null) {
                            leaveRatingController.execute(username, password, bookID, rating);
                        } else {
                            feedbackLabel.setText("Error: Book not found for ID: " + bookID);
                        }
                    }
                } catch (NumberFormatException e) {
                    feedbackLabel.setText("Error: Invalid rating value. Please enter a number.");
                }
            }
        });

        this.add(title);
        this.add(Box.createVerticalStrut(20));
        this.add(usernameInfo);
        this.add(username);
        this.add(Box.createVerticalStrut(10));

        this.add(bookInfo);
        this.add(ratingInfo);
        this.add(feedbackLabel);
        this.add(Box.createVerticalStrut(20));
        this.add(buttons);
    }

    private Listing fetchListingByBookID(String bookID) {
        // Placeholder for fetching a Listing object based on bookID
        // Replace with actual logic to retrieve a Listing
        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LeaveRatingState state = (LeaveRatingState) evt.getNewValue();
            username.setText(state.getUsername());

            // Check for error or success message and update feedback label
            if (state.getError() != null && !state.getError().isEmpty()) {
                feedbackLabel.setText("Error: " + state.getError());
            } else if (state.getSuccessMessage() != null && !state.getSuccessMessage().isEmpty()) {
                feedbackLabel.setText(state.getSuccessMessage());
            }
        }
    }

    public void setLeaveRatingController(LeaveRatingController leaveRatingController) {
        this.leaveRatingController = leaveRatingController;
    }

    public String getViewName() {
        return viewName;
    }
}
