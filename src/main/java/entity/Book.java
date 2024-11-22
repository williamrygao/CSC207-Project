package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a book in the marketplace.
 * The book is identified by a unique ID from the Google Books API.
 * Multiple sellers can offer the same book.
 */
public class Book {

    private String bookId;
    private String title;
    private List<String> authors;
    private String description;
    private List<String> genre;
    private float rating;
    private List<String> sellers;

    public Book(String bookId, String title, List<String> authors, String description, List<String> genre) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.genre = genre;
        this.rating = 0;
        this.sellers = new ArrayList<>();
    }

    // Getters and Setters for title, authors, description

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    /**
     * Adds an author to the list of authors for the book.
     *
     * @param author the genre to be added
     */
    public void addAuthor(String author) {
        this.authors.add(author);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    /**
     * Adds a genre to the list of genres/categories for the book.
     *
     * @param addition the genre to be added
     */
    public void addGenre(String addition) {
        this.genre.add(addition);
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public List<String> getSellers() {
        return sellers;
    }

    /**
    * Method to add a seller who is offering this book.
     * @param sellerId represents the sellerID
     */
    public void addSeller(String sellerId) {
        sellers.add(sellerId);
    }

    /**
     * Override the equals method to compare books based on their bookId.
     * Two books are considered equal if they have the same bookId.
     *
     * @param o the object to compare this book to
     * @return true if the books have the same bookId, false otherwise
     */

    @Override
    public boolean equals(Object o) {
        final Book book = (Book) o;
        return Objects.equals(bookId, book.bookId);
    }

    /**
     * Override the hashCode method to ensure consistency with equals.
     *
     * @return the hash code of the book based on its bookId
     */
    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }

    /**
     * Override the toString method to provide a custom string representation of the book.
     *
     * @return a string representation of the book
     */
    @Override
    public String toString() {
        return "Book{"
                +
                "bookId='" + bookId + '\''
                +
                ", title='" + title + '\''
                +
                ", authors='" + authors + '\''
                +
                ", description='" + description + '\''
                +
                ", genre='" + genre + '\''
                +
                ", sellers=" + sellers
                +
                '}';
    }
}
