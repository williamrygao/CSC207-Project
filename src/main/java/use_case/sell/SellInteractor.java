package use_case.sell;

import entity.Book;
import entity.BookFactory;
import entity.Listing;

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
        final String sellingPrice = sellInputData.getSellingPrice();
        final String seller = sellInputData.getUsername();

        final Listing listing = new Listing(bookID, book, sellingPrice, seller, true);
        bookDataAccessObject.save(listing);

        final SellOutputData sellOutputData = new SellOutputData(sellInputData.getUsername(), listing, false);
        userPresenter.prepareSuccessView(sellOutputData);
    }
}
