package use_case.leave_rating;

import entity.Listing;
import entity.Rating;
import entity.User;

public interface LeaveRatingDataAccessInterface {
    boolean existsByBookID(String bookID);

    void save(Rating rating);

    Rating getRatingsByBookID(String bookID);

    String getCurrentUsername();

    void setCurrentUsername(String username);

    void leaveRating(User user, Rating rating, Integer newRating, Listing listing);
}
