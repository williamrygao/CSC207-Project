package use_case.sell;

import entity.Book;
import entity.BookFactory;

/**
 * The Sell Interactor.
 */
public class SellInteractor implements SellInputBoundary {
    private final SellUserDataAccessInterface userDataAccessObject;
    private final SellBookDataAccessInterface bookDataAccessObject;
    private final SellOutputBoundary userPresenter;
    
    public SellInteractor(SellUserDataAccessInterface sellUserDataAccessInterface,
                          SellBookDataAccessInterface sellBookDataAccessInterface,
                          SellOutputBoundary sellOutputBoundary) {
        this.userDataAccessObject = sellUserDataAccessInterface;
        this.bookDataAccessObject = sellBookDataAccessInterface;
        this.userPresenter = sellOutputBoundary;
    }

    @Override
    public void execute(SellInputData sellInputData) {
        final String bookID = sellInputData.getBookID();
        final Book book = BookFactory.createBook(bookID);
        bookDataAccessObject.save(book);

        final SellOutputData sellOutputData = new SellOutputData(sellInputData.getUsername(), book, false);
        userPresenter.prepareSuccessView(sellOutputData);
    }
}
