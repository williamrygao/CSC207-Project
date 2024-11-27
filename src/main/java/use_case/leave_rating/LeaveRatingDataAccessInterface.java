package use_case.leave_rating;

import entity.Listing;
import entity.Rating;
import entity.User;

public interface LeaveRatingDataAccessInterface {
    String getCurrentUsername();

    void setCurrentUsername(String username);

    void addToWishlist(User user, Rating rating, Integer newRating, Listing listing);
}
