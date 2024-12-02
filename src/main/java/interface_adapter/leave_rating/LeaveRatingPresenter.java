package interface_adapter.leave_rating;

import javax.swing.JOptionPane;

public class LeaveRatingPresenter {

    // This method shows a success message to the user
    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // This method shows an error message to the user
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // This method shows an invalid rating message (e.g., if the rating is outside the valid range)
    public void showInvalidRatingMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Invalid Rating", JOptionPane.WARNING_MESSAGE);
    }
}