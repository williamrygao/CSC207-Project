package data_access;

import entity.BookFactory;
import entity.Listing;
import entity.Rating;
import entity.User;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.login.LoginListingDataAccessInterface;
import use_case.leave_rating.LeaveRatingDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The DAO for rating data
 */
public class FireBaseRatingDataAccessObject implements LeaveRatingDataAccessInterface {

    @Override
    public String getCurrentUsername() {
        return "";
    }

    @Override
    public void setCurrentUsername(String username) {

    }

    @Override
    public void addToWishlist(User user, Rating rating, Integer newRating, Listing listing) {

    }
}
