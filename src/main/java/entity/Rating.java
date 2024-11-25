package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A rating for a book.
 */
public class Rating {

    private Book book;
    private List<Double> ratings;

    // Constructor, getters, and setters
    public Rating(Book book) {
        this.book = book;
        this.ratings = new ArrayList<>();
    }

    // Getters and Setters
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    // Method to add a rating to the list (validated between 1 and 10)
    public void addRating(double rating) {
        if (rating >= 1 && rating <= 10) {
            this.ratings.add(rating);
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 10.");
        }
    }

    // Method to calculate the average rating
    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0; // No ratings yet, return 0
        }
        double total = 0;
        for (Double rating : ratings) {
            total += rating;
        }
        return total / ratings.size(); // Return the average
    }
}
