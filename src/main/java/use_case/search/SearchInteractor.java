package use_case.search;

import data_access.FirebaseListingDataAccessObject;
import data_access.FirebaseUserDataAccessObject;
import entity.Book;
import entity.BookFactory;
import entity.Listing;

/**
 * The Search Interactor.
 */
public class SearchInteractor implements SearchInputBoundary {
    private final FirebaseUserDataAccessObject userDataAccessObject;
    private final FirebaseListingDataAccessObject bookDataAccessObject;
    private final SearchOutputBoundary userPresenter;

    public SearchInteractor(FirebaseUserDataAccessObject userDataAccessObject,
                            FirebaseListingDataAccessObject bookDataAccessObject,
                            SearchOutputBoundary searchOutputBoundary) {
        this.userPresenter = searchOutputBoundary;
        this.bookDataAccessObject = bookDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        final String bookID = searchInputData.getBookID();
        final Book book = BookFactory.createBook(bookID);
        final String seller = searchInputData.getUsername();
        final String price = searchInputData.getPrice();

        final Listing listing = new Listing(bookID, book, price, seller, true);
        bookDataAccessObject.save(listing);

        final SearchOutputData searchOutputData = new SearchOutputData(searchInputData.getUsername(), listing, false);
        userPresenter.prepareSuccessView(searchOutputData);
    }
}
