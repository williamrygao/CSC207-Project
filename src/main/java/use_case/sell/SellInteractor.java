package use_case.sell;

import entity.book.Book;
import entity.book.BookFactory;
import entity.listing.Listing;

/**
 * The Sell Interactor.
 */
public class SellInteractor implements SellInputBoundary {
    private final SellListingDataAccessInterface listingDataAccessObject;
    private final SellOutputBoundary userPresenter;
    private final BookFactory bookFactory;

    public SellInteractor(SellListingDataAccessInterface listingDataAccessObject,
                          SellOutputBoundary sellOutputBoundary,
                          BookFactory bookFactory) {
        this.listingDataAccessObject = listingDataAccessObject;
        this.userPresenter = sellOutputBoundary;
        this.bookFactory = bookFactory;
    }

    @Override
    public void execute(SellInputData sellInputData) {
        final String bookID = sellInputData.getBookID();
        final Book book = bookFactory.createBook(bookID);
        final String price = sellInputData.getPrice();
        final String seller = sellInputData.getUsername();

        // check to see if this seller already listed this book
        if (listingDataAccessObject.exists(bookID, seller)) {
            userPresenter.prepareFailView(seller + " has already listed " + book.getTitle() + " for sale!");
        }
        else {
            final Listing listing = new Listing(bookID, book, price, seller, true);
            listingDataAccessObject.save(listing);

            final SellOutputData sellOutputData = new SellOutputData(sellInputData.getUsername(), listing, false);
            userPresenter.prepareSuccessView(sellOutputData);
        }
    }

    @Override
    public String getBookPrice(String bookID) {
        final Book book = bookFactory.createBook(bookID);
        return book.getPrice();
    }
}
