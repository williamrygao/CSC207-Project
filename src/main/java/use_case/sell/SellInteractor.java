package use_case.sell;

import entity.Book;
import entity.BookFactory;
import entity.User;
import entity.UserFactory;

/**
 * The Sell Interactor.
 */
public class SellInteractor implements SellInputBoundary {
    private final SellUserDataAccessInterface userDataAccessObject;
    private final SellBookDataAccessInterface bookDataAccessObject;
    private final SellOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SellInteractor(SellUserDataAccessInterface sellUserDataAccessInterface,
                          SellBookDataAccessInterface sellBookDataAccessInterface,
                          SellOutputBoundary signupOutputBoundary,
                          UserFactory userFactory) {
        this.userDataAccessObject = sellUserDataAccessInterface;
        this.bookDataAccessObject = sellBookDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SellInputData sellInputData) {
        final String bookID = sellInputData.getBookID();
        final Book book = BookFactory.createBook(bookID);
        bookDataAccessObject.save(book);

        final User user = userFactory.create(sellInputData.getUsername(), sellInputData.getPassword());
        userDataAccessObject.save(user);

        final SellOutputData sellOutputData = new SellOutputData(user.getName(), book, false);
        userPresenter.prepareSuccessView(sellOutputData);
    }
}
