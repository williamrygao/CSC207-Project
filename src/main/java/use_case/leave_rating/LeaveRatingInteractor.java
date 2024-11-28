package use_case.leave_rating;

import entity.Listing;
import entity.User;
import entity.UserFactory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * The LeaveRating Interactor for Firebase Realtime Database.
 */
public class LeaveRatingInteractor implements LeaveRatingInputBoundary {

    private final DatabaseReference databaseReference;
    private final LeaveRatingOutputBoundary leaveRatingPresenter;
    private final UserFactory userFactory;

    /**
     * Constructor for LeaveRatingInteractor.
     * @param leaveRatingOutputBoundary the presenter responsible for preparing output
     * @param userFactory user factory to create user instances
     */
    public LeaveRatingInteractor(final LeaveRatingOutputBoundary leaveRatingOutputBoundary, UserFactory userFactory) {
        this.databaseReference = FirebaseDatabase.getInstance().getReference("books");
        this.leaveRatingPresenter = leaveRatingOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Executes the LeaveRating use case.
     * @param leaveRatingInputData the input data containing rating information
     */
    @Override
    public void execute(final LeaveRatingInputData leaveRatingInputData) {
        String username = leaveRatingInputData.getUsername();
        String password = leaveRatingInputData.getPassword();
        User user = userFactory.create(username, password);
        Listing listing = leaveRatingInputData.getListing();
        String bookId = leaveRatingInputData.getBookid();
        Integer newRating = leaveRatingInputData.getNewRating();

        if (user == null) {
            // If user authentication fails
            leaveRatingPresenter.prepareFailView("Authentication failed for user: " + username);
            return;
        }

        // Reference to the book in Firebase Realtime Database
        DatabaseReference bookRef = databaseReference.child(bookId);

        // Add the new rating to the "ratings" array of this book
        addRating(bookRef, newRating, username);
    }

    /**
     * Adds a new rating to the book and updates the average rating.
     * @param bookRef the reference to the book node in Firebase Realtime Database
     * @param newRating the new rating value to be added
     * @param username the username of the user leaving the rating
     */
    private void addRating(DatabaseReference bookRef, Integer newRating, String username) {
        bookRef.child("ratings").push().setValue(newRating)
                .addOnSuccessListener(aVoid -> {
                    updateAverageRating(bookRef);
                    notifySuccess(username);
                })
                .addOnFailureListener(e -> notifyFailure("Failed to leave rating: " + e.getMessage()));
    }

    /**
     * Updates the average rating of the book in Firebase Realtime Database.
     * @param bookRef the reference to the book node in Firebase
     */
    private void updateAverageRating(DatabaseReference bookRef) {
        bookRef.child("ratings").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    List<Integer> ratings = (List<Integer>) dataSnapshot.getValue();
                    if (ratings != null && !ratings.isEmpty()) {
                        double average = calculateAverageRating(ratings);
                        bookRef.child("averageRating").setValue(average)
                                .addOnFailureListener(e -> e.printStackTrace());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log or handle error in fetching ratings
                databaseError.toException().printStackTrace();
            }
        });
    }

    /**
     * Calculates the average of the ratings.
     * @param ratings the list of ratings to calculate the average from
     * @return the calculated average rating
     */
    private double calculateAverageRating(List<Integer> ratings) {
        return ratings.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    /**
     * Prepares the success output for the presenter.
     * @param username the username of the user leaving the rating
     */
    private void notifySuccess(String username) {
        LeaveRatingOutputData outputData = new LeaveRatingOutputData(username, false);
        leaveRatingPresenter.prepareSuccessView(outputData);
    }

    /**
     * Prepares the failure output for the presenter.
     * @param errorMessage the error message to be shown
     */
    private void notifyFailure(String errorMessage) {
        leaveRatingPresenter.prepareFailView(errorMessage);
    }
}
