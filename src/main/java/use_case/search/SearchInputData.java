package use_case.search;

/**
 * The Input Data for the Search Use Case.
 */
public class SearchInputData {

    private final String username;
    private final String bookID;
    private final String authors;
    private final String title;
    private final String price;

    public SearchInputData(String username, String bookID, String authors, String title, String price) {
        this.username = username;
        this.bookID = bookID;
        this.authors = authors;
        this.title = title;
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public String getBookID() {
        return bookID;
    }

    public String getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
