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
=======
import java.util.LinkedList;
import java.util.List;

/**
 * A class that represents the rating of a book.
 */
public class Rating {
    public static final int MAX = 10;
    public static final int MIN = 1;
    private final List<Integer> ratings;
    private final String bookId;

    /**
     * Constructs a Rating object for the specified book.
     * @param bookId the title of the book
     * @throws IllegalArgumentException when rating is out of boundary.
     */
    public Rating(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book name cannot be null or empty.");
        }
        this.bookId = bookId;
        ratings = new LinkedList<>();
    }

    /**
     * Adds a rating to the list if it is within the valid range.
     * @param rating the rating to be added
     * @throws IllegalArgumentException if the rating is out of the valid range
     */
    public void addRating(int rating) {
        if (rating >= MIN && rating <= MAX) {
            ratings.add(rating);
        }
        else {
            throw new IllegalArgumentException("Invalid rating: " + rating + ". Rating must be between " + MIN + " and " + MAX + ".");
        }
    }

    /**
     * Calculates and returns the average rating.
     * @return the average rating, or 0.0 if there are no ratings
     */

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        // Use Stream API for concise summation
        final int sum = ratings.stream().mapToInt(Integer::intValue).sum();
        return sum / (double) ratings.size();
    }

    /**
     * Returns a copy of the list of ratings to maintain encapsulation.
     * @return a copy of the list of ratings
     */
    public List<Integer> getRatings() {
        return new LinkedList<>(ratings);
    }

    /**
     * Returns the total number of ratings.
     * @return the total number of ratings
     */
    public int getRatingNumber() {
        return ratings.size();
    }

    /**
     * Returns the name of the book associated with the ratings.
     * @return the book name
     */
    public String getBookId() {
        return bookId;

    }
}
