package use_case.leave_rating;

import entity.Listing;
import entity.User;
import entity.UserFactory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
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
    /**
     * The FirebaseDatabase reference to books.
     */
    private final DatabaseReference databaseReference;
    /**
     * The LeaveRatingPresenter.
     */
    private final LeaveRatingOutputBoundary leaveRatingPresenter;
    private final UserFactory userFactory;

    /**
     * LeaveRatingInteractor constructor.
     * @param leaveRatingOutputBoundary the LeaveRatingOutputBoundary
     * @param userFactory user factory
     */
    public LeaveRatingInteractor(final LeaveRatingOutputBoundary leaveRatingOutputBoundary, UserFactory userFactory) {
        this.databaseReference = FirebaseDatabase.getInstance().getReference("books");
        this.leaveRatingPresenter = leaveRatingOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Execute the LeaveRating use case.
     * @param leaveRatingInputData the input data
     */
    @Override
    public void execute(final LeaveRatingInputData leaveRatingInputData) {
        final String username = leaveRatingInputData.getUsername();
        final String password = leaveRatingInputData.getPassword();
        final User user = userFactory.create(username, password);
        final Listing listing = leaveRatingInputData.getListing();
        final String bookId = leaveRatingInputData.getBookid();
        final Integer newRating = leaveRatingInputData.getNewRating();

        if (user != null) { // Authentication check
            // Reference to the book in Firebase Realtime Database
            DatabaseReference bookRef = databaseReference.child(bookId);

            // Add the new rating to the "ratings" array of this book
            bookRef.child("ratings").push().setValue(newRating)
                    .addOnSuccessListener(aVoid -> {
                        // After the new rating is added, optionally update the average rating
                        updateAverageRating(bookRef);

                        // Prepare success output
                        LeaveRatingOutputData outputData = new LeaveRatingOutputData(username, false);
                        leaveRatingPresenter.prepareSuccessView(outputData);
                    })
                    .addOnFailureListener(e -> {
                        // Handle failure
                        leaveRatingPresenter.prepareFailView("Failed to leave rating: " + e.getMessage());
                    });
        }
        else {
            // Authentication failed
            leaveRatingPresenter.prepareFailView("Authentication failed for user: " + username);
        }
    }

    /**
     * Method to calculate and update the average rating of the book in Firebase Realtime Database.
     * @param bookRef the reference to the book node in Firebase Realtime Database
     */
    private void updateAverageRating(DatabaseReference bookRef) {
        // Retrieve the current list of ratings
        bookRef.child("ratings").get()
                .addOnSuccessListener(dataSnapshot -> {
                    if (dataSnapshot.exists()) {
                        List<Integer> ratings = (List<Integer>) dataSnapshot.getValue();
                        if (ratings != null && !ratings.isEmpty()) {
                            // Calculate the average rating
                            double average = ratings.stream()
                                    .mapToInt(Integer::intValue)
                                    .average()
                                    .orElse(0.0);

                            // Update the averageRating field in Firebase
                            bookRef.child("averageRating").setValue(average)
                                    .addOnSuccessListener(aVoid -> {
                                        // Optionally log success or return a response
                                    })
                                    .addOnFailureListener(e -> {
                                        // Handle failure to update average rating
                                    });
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle failure to fetch the ratings
                });
    }
}
