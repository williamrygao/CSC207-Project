package use_case.sell;

import entity.Book;

/**
 * Output Data for the Signup Use Case.
 */
public class SellOutputData {

    private final String username;
    private final Book book;

    private final boolean useCaseFailed;

    public SellOutputData(String username, Book book, boolean useCaseFailed) {
        this.username = username;
        this.book = book;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
