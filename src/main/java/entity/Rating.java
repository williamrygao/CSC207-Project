package entity;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;  // You can also use other dynamic List implementations

/**
 * A class that represents the rating of a book.
 */
public class Rating {
    private List<Integer> ratings;

    // Constructor initializes the ratings list
    public Rating() {
        ratings = new LinkedList<>();
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
        Iterator<Integer> iterator = ratings.iterator();
        while (iterator.hasNext()) {
            sum += iterator.next();  // Sum up all the ratings
        }
        return sum / (double) ratings.size();
    }

    // Get the list of all ratings
    public List<Integer> getRatings() {
        return new LinkedList<>(ratings);  // Return a copy to maintain encapsulation
    }

    // Get the total number of ratings
    public int getRatingNumber() {
        return ratings.size();
    }
}
