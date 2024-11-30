package entity.book;

import java.util.Objects;

/**
 * Represents a book in the marketplace.
 * The book is identified by a unique ID from the Google Books API.
 * Multiple sellers can offer the same book.
 */
public class Book {

    private String bookId;
    private String title;
    private String authors;
    private String genre;
    private String price;
    private float rating;

    public Book(String bookId, String title, String authors, String genre, String price, float rating) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.genre = genre;
        this.price = price;
        this.rating = rating;
    }

    public Book(String bookId, String title, String authors, String genre, String price) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.genre = genre;
        this.price = price;
        this.rating = 0;
    }

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

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
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
                ", genre='" + genre + '\''
                +
                '}';
    }

    /**
     * Update book rating.
     * @param bookRating the book's rating
     */
    public void updateRating(int bookRating) {
        this.rating = bookRating;
    }
}
