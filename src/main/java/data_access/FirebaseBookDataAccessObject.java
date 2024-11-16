package data_access;

import entity.Book;
import entity.BookFactory;
import use_case.sell.SellBookDataAccessInterface;

/**
 * The DAO for book data.
 */
public class FirebaseBookDataAccessObject implements SellBookDataAccessInterface {
    private final BookFactory bookFactory;

    /**
     * FirebaseBookDataAccessObject.
     * @param bookFactory setting bookFactory
     */
    public FirebaseBookDataAccessObject(final BookFactory bookFactory) {
        this.bookFactory = bookFactory;
    }

    @Override
    public boolean existsByBookID(String bookID) {
        return false;
    }

    @Override
    public void save(Book book) {

    }
}
