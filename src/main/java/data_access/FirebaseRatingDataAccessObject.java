package data_access;

import entity.Listing;
import entity.Rating;
import entity.User;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.leave_rating.LeaveRatingDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The DAO for rating data.
 */
public class FirebaseRatingDataAccessObject implements LeaveRatingDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    private final OkHttpClient httpClient;
    private final String firebaseBaseUrl;
    private String userId;
    private String bookId;

    /**
     * FirebaseRatingDataAccessObject constructor.
     *
     * @param firebaseBaseUrl Base URL for the Firebase database.
     */
    public FirebaseRatingDataAccessObject(final String firebaseBaseUrl) {
        this.firebaseBaseUrl = firebaseBaseUrl;
        this.httpClient = new OkHttpClient();
    }

    @Override
    public boolean existsByBookID(String bookID) {
        String url = firebaseBaseUrl + "/ratings/" + bookID + ".json";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() == SUCCESS_CODE) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                return jsonResponse.length() > 0;
            }
        } catch (IOException | JSONException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(final Rating rating) {
        String url = firebaseBaseUrl + "/ratings/" + rating.getBookId() + ".json";
        JSONArray jsonRatings = new JSONArray(rating.getRatings());

        JSONObject jsonRating = new JSONObject();
        try {
            jsonRating.put("ratings", jsonRatings);

            RequestBody body = RequestBody.create(
                    jsonRating.toString(), MediaType.parse(CONTENT_TYPE_JSON));

            Request request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (response.code() != SUCCESS_CODE) {
                    System.out.println("Failed to save rating: " + response.message());
                }
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rating getRatingsByBookID(String bookID) {
        String url = firebaseBaseUrl + "/ratings/" + bookID + ".json";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        Rating rating = new Rating(bookID);

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() == SUCCESS_CODE) {
                String responseBody = response.body().string();
                if (responseBody != null && !responseBody.equals("null")) {
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    JSONArray ratingsArray = jsonResponse.getJSONArray("ratings");

                    // Convert JSON array into a List of ratings
                    for (int i = 0; i < ratingsArray.length(); i++) {
                        rating.addRating(ratingsArray.getInt(i));
                    }
                }
            }
        } catch (IOException | JSONException exception) {
            exception.printStackTrace();
        }

        return rating;
    }

    /**
     * Retrieves all ratings from the Firebase database.
     *
     * @return a list of ratings or an empty list if none exist or an error occurs.
     */
    public List<Rating> getAllRatings() {
        String url = firebaseBaseUrl + "/ratings.json";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        List<Rating> ratingsList = new ArrayList<>();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() == SUCCESS_CODE) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);

                // Iterate over JSON keys to extract ratings
                Iterator<String> keys = jsonResponse.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    JSONObject jsonRating = jsonResponse.getJSONObject(key);

                    // Extract ratings for each book and create Rating objects
                    JSONArray ratingsArray = jsonRating.getJSONArray("ratings");
                    Rating rating = new Rating(key);

                    for (int i = 0; i < ratingsArray.length(); i++) {
                        rating.addRating(ratingsArray.getInt(i));
                    }

                    ratingsList.add(rating);
                }
            } else {
                System.err.println("Failed to fetch ratings: " + response.message());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return ratingsList;
    }

    @Override
    public String getCurrentUsername() {
        return userId;
    }

    public String getCurrentBookId() {
        return bookId;
    }

    @Override
    public void setCurrentUsername(String username) {
        this.userId = username;
    }

    @Override
    public void leaveRating(User user, Rating rating, Integer newRating, Listing listing) {
        // Get the book's ID
        String bookId = listing.getBook().getBookId();
        String url = firebaseBaseUrl + "/ratings/" + bookId + ".json";

        // Fetch the current rating list from Firebase
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() == SUCCESS_CODE) {
                String responseBody = response.body().string();

                // Check if the book has ratings already
                JSONObject jsonResponse = new JSONObject(responseBody);
                List<Integer> currentRatings = new ArrayList<>();

                if (jsonResponse.length() > 0) {
                    // Parse existing ratings
                    JSONArray existingRatingsArray = jsonResponse.getJSONArray("ratings");
                    for (int i = 0; i < existingRatingsArray.length(); i++) {
                        currentRatings.add(existingRatingsArray.getInt(i));
                    }
                }

                // Add the new rating to the list
                currentRatings.add(newRating);

                // Update the rating on Firebase (ensure one list for the book)
                JSONObject updatedRatingJson = new JSONObject();
                updatedRatingJson.put("ratings", new JSONArray(currentRatings));

                RequestBody body = RequestBody.create(
                        updatedRatingJson.toString(), MediaType.parse(CONTENT_TYPE_JSON));
                request = new Request.Builder()
                        .url(url)
                        .put(body)
                        .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                        .build();

                try (Response updateResponse = httpClient.newCall(request).execute()) {
                    if (updateResponse.code() != SUCCESS_CODE) {
                        System.out.println("Failed to update the rating: " + updateResponse.message());
                    }
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


    private Rating parseRatingFromJson(JSONObject jsonResponse) throws JSONException {
        String bookId = jsonResponse.keys().next();
        JSONObject ratingData = jsonResponse.getJSONObject(bookId);
        Rating rating = new Rating(bookId);

        List<Integer> currentRatings = ratingData.getJSONArray("ratings").toList();
        for (Integer rate : currentRatings) {
            rating.addRating(rate);
        }

        return rating;
    }
}