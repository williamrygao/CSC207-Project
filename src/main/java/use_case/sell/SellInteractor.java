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
    private final SellBookDataFetcher sellBookDataFetcher;
    private final SellOutputBoundary userPresenter;

    public SellInteractor(SellUserDataAccessInterface sellUserDataAccessInterface,
                          SellBookDataAccessInterface sellBookDataAccessInterface,
                          SellOutputBoundary sellOutputBoundary,
                          SellBookDataFetcher sellBookDataFetcher) {
        this.userDataAccessObject = sellUserDataAccessInterface;
        this.bookDataAccessObject = sellBookDataAccessInterface;
        this.userPresenter = sellOutputBoundary;
        this.sellBookDataFetcher = sellBookDataFetcher;
    }

    @Override
    public void execute(SellInputData sellInputData) {
        final String bookID = sellInputData.getBookID();
        final Book book = BookFactory.createBook(bookID);
        final String price = sellInputData.getPrice();
        final String seller = sellInputData.getUsername();

        final Listing listing = new Listing(bookID, book, price, seller, true);
        bookDataAccessObject.save(listing);

        final SellOutputData sellOutputData = new SellOutputData(sellInputData.getUsername(), listing, false);
        userPresenter.prepareSuccessView(sellOutputData);
    }

    @Override
    public String getBookPrice(String BookID) {
        return sellBookDataFetcher.getBookPrice(BookID);
    }
}
