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

    private String author;
    private String description;
    private List<String> sellers;

    public Book(String bookId) {
        this.bookId = bookId;
        this.sellers = new ArrayList<>();
    }

    // Getters and Setters for title, author, description

    private String authors;
    private String description;
    private String genre;
    private float rating;
    private List<String> sellers;

    public Book(String bookId, String title, String authors, String description, String genre) {
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


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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


    // Method to add a seller who is offering this book

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

                ", author='" + author + '\''
                +
                ", description='" + description + '\''
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


    public void updateRating(int rating) {

    }

}
