package use_case.leave_rating;

import entity.Book;

/**
 * The Leave Rating Interactor.
 */
public class LeaveRatingInteractor implements LeaveRatingInputBoundary {

    private final LeaveRatingDataAccessInterface leaveRatingDataAccessObject;
    private final LeaveRatingOutputBoundary leaveRatingPresenter;

    public LeaveRatingInteractor(LeaveRatingDataAccessInterface leaveRatingDataAccessInterface,
                                    LeaveRatingOutputBoundary leaveRatingOutputBoundary) {
        this.leaveRatingDataAccessObject = leaveRatingDataAccessInterface;
        this.leaveRatingPresenter = leaveRatingOutputBoundary;
    }

    @Override
    public void execute(LeaveRatingInputData leaveRatingInputData) {

        // retrieve info from Input Data Object
        final String bookID = leaveRatingInputData.getBookID();
        final int rating = leaveRatingInputData.getRating();

        // retrieve the book in database then update its rating
        final Book book = leaveRatingDataAccessObject.getBook(bookID);
        book.updateRating(rating);

        // prepare views using the presenter
        final LeaveRatingOutputData leaveRatingOutputData = new leaveRatingOutputData();
    }

}
