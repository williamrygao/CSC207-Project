package use_case.leave_rating;

import entity.Listing;
import entity.User;
import entity.UserFactory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import data_access.FirebaseInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        // Use FirebaseInitializer to get a reference to the database
        this.databaseReference = FirebaseInitializer.getRealtimeDatabaseReference();
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
            // If user authentication fails, notify failure without error message
            leaveRatingPresenter.prepareFailView("Authentication failed for user: " + username);
            return;
        }

        // Reference to the book in Firebase Realtime Database
        DatabaseReference bookRef = databaseReference.child("books").child(bookId);

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
                .addOnFailureListener(e -> {
                    // Ignore failure without any error message
                    notifyFailure();
                });
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
                    List<Integer> ratings = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Integer rating = snapshot.getValue(Integer.class);
                        if (rating != null) {
                            ratings.add(rating);
                        }
                    }

                    if (!ratings.isEmpty()) {
                        long average = (long) calculateAverageRating(ratings);
                        bookRef.child("averageRating").setValueAsync(average);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // No error handling or logging needed
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
     * Prepares the failure output for the presenter (no error message).
     */
    private void notifyFailure() {
        leaveRatingPresenter.prepareFailView("Failed to leave rating.");
    }
}
