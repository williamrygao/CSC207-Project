package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the rating of a book.
 */
public class Rating {
    private List<Integer> ratings;

    // Constructor initializes the ratings list
    public Rating() {
        ratings = new ArrayList<>();
    }

    // Add a new rating (from 1 to 10)
    public void addRating(int rating) {
        if (rating >= 1 && rating <= 10) {
            ratings.add(rating);
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 10.");
        }
    }

    // Calculate and return the average rating
    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }

        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return sum / (double) ratings.size();
    }

    // Getter for the list of ratings (if needed)
    public List<Integer> getRatings() {
        return ratings;
    }
}
