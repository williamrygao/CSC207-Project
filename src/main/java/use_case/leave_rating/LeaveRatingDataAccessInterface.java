package use_case.leave_rating;

import entity.Book;

public interface LeaveRatingDataAccessInterface {

    Book getBook(String bookID);
}
