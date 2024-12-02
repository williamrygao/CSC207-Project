package use_case.search;

import data_access.FirebaseListingDataAccessObject;
import data_access.FirebaseUserDataAccessObject;
import entity.book.Book;
import entity.book.BookFactory;
import entity.listing.Listing;

/**
 * The Search Interactor.
 */
public class SearchInteractor implements SearchInputBoundary {
    private final FirebaseUserDataAccessObject userDataAccessObject;
    private final FirebaseListingDataAccessObject bookDataAccessObject;
    private final SearchOutputBoundary userPresenter;
    private final BookFactory bookFactory;

    public SearchInteractor(FirebaseUserDataAccessObject userDataAccessObject,
                            FirebaseListingDataAccessObject bookDataAccessObject,
                            SearchOutputBoundary searchOutputBoundary,
                            BookFactory bookFactory) {
        this.userPresenter = searchOutputBoundary;
        this.bookDataAccessObject = bookDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.bookFactory = bookFactory;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        final String bookID = searchInputData.getBookID();
        final Book book = bookFactory.createBook(bookID);
        final String seller = searchInputData.getUsername();
        final String price = searchInputData.getPrice();

        final Listing listing = new Listing(bookID, book, price, seller, true);
        bookDataAccessObject.save(listing);

        final SearchOutputData searchOutputData = new SearchOutputData(searchInputData.getUsername(), listing, false);
        userPresenter.prepareSuccessView(searchOutputData);
    }
}
