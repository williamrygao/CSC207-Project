package data_access;

import entity.listing.Listing;
import entity.Rating;
import entity.book.Book;
import entity.user.User;
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
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    JSONObject jsonResponse = new JSONObject(responseBody.string());
                    return jsonResponse.length() > 0; // Book exists if JSON is not empty
                } else {
                    System.err.println("Response body is null for URL: " + url);
                }
            } else {
                System.err.println("Unexpected response code: " + response.code() + " for URL: " + url);
            }
        } catch (IOException | JSONException exception) {
            System.err.println("Error occurred while checking existence of book ID: " + bookID);
            exception.printStackTrace();
        }
        return false; // Default to false if an error occurs
    }

    public float fetchAverageRatingFromDatabase(String bookId) {
        String url = firebaseBaseUrl + "/ratings/" + bookId + ".json";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();

                // Handle empty or null responses gracefully
                if (responseBody.equals("null") || responseBody.trim().isEmpty()) {

                    return 0.0f; // Return default rating
                }

                try {
                    // Parse the JSON response
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    JSONArray ratingsArray = jsonResponse.optJSONArray("ratings");

                    if (ratingsArray != null && ratingsArray.length() > 0) {
                        int total = 0;
                        int validRatingsCount = 0;

                        // Process each rating, filtering out null or invalid values
                        for (int i = 0; i < ratingsArray.length(); i++) {
                            if (!ratingsArray.isNull(i)) {
                                total += ratingsArray.getInt(i);
                                validRatingsCount++;
                            }
                        }

                        // Calculate average if valid ratings are found
                        if (validRatingsCount > 0) {
                            return total / (float) validRatingsCount;
                        } else {
                            System.err.println("No valid ratings found for bookId: " + bookId);
                        }
                    } else {

                    }
                } catch (JSONException e) {
                    // Handle malformed JSON
                    System.err.println("Error parsing JSON for bookId: " + bookId);

                }
            } else {
                // Log unsuccessful responses
                System.err.println("Failed to fetch ratings for bookId: " + bookId);
                System.err.println("Response code: " + response.code() + ", message: " + response.message());
            }
        } catch (IOException e) {
            // Log network-related issues
            System.err.println("Error fetching average rating for bookId: " + bookId);
        }

        return 0.0f; // Default to 0.0 if an error occurs
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


    public List<Listing> filterByRating(int minRating) {

        // Collect all ratings
        final List<Rating> allRatings = this.getAllRatings();

        // filter through ratings to collect the bookIDs of those with an average rating at or above minimum
        final List<String> filteredBookIDs = new ArrayList<>();
        for (Rating rating : allRatings) {
            if (rating.getAverageRating() >= minRating) {
                filteredBookIDs.add(rating.getBookId());
            }
        }

        // retrieve all listings
        final List<Listing> allListings = new ArrayList<>();
        final String url = firebaseBaseUrl + "/listings.json";
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() == SUCCESS_CODE) {
                final String responseBody = response.body().string();
                final JSONObject jsonResponse = new JSONObject(responseBody);

                // Iterate over JSON keys to extract listings
                final Iterator<String> keys = jsonResponse.keys();
                while (keys.hasNext()) {
                    final String key = keys.next();
                    final JSONObject jsonListing = jsonResponse.getJSONObject(key);

                    // Extract attributes from JSON and create a Listing
                    final String bookID = jsonListing.getString("bookID");
                    final String title = jsonListing.getString("title");
                    final String authors = jsonListing.getString("authors");
                    final String genre = jsonListing.getString("genre");
                    final String bookPrice = jsonListing.getString("bookPrice");
                    final String listingPrice = jsonListing.getString("listingPrice");
                    final String seller = jsonListing.getString("seller");
                    final float rating = jsonListing.getFloat("rating");
                    final boolean isAvailable = jsonListing.getBoolean("isAvailable");

                    final Book book = new Book(bookID, title, authors, genre, bookPrice, rating);
                    // Create the Listing object using the BookFactory and extracted attributes
                    allListings.add(new Listing(
                            bookID,
                            book,
                            listingPrice,
                            seller,
                            isAvailable
                    ));
                }
            }
            else {
                System.err.println("Failed to fetch listings: " + response.message());
            }
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        // filter through all listings by whether their IDs match the list of filteredBookIDs
        final List<Listing> filteredListings = new ArrayList<>();
        for (Listing listing : allListings) {
            if (filteredBookIDs.contains(listing.getListingID())) {
                filteredListings.add(listing);
            }
        }

        // return the filtered listings
        return filteredListings;
    }

    private Rating parseRatingFromJson(JSONObject jsonResponse) throws JSONException {
        // Retrieve the first key (book ID) from the JSON response
        String bookId = jsonResponse.keys().next();
        // Get the rating data for the book ID
        JSONObject ratingData = jsonResponse.getJSONObject(bookId);
        // Create a new Rating object for this book
        Rating rating = new Rating(bookId);

        // Parse the ratings array
        JSONArray ratingsArray = ratingData.getJSONArray("ratings");
        List<Integer> currentRatings = new ArrayList<>();

        // Iterate through the JSONArray and convert it into a List<Integer>
        for (int i = 0; i < ratingsArray.length(); i++) {
            currentRatings.add(ratingsArray.getInt(i));
        }

        // Add ratings to the Rating object
        for (Integer rate : currentRatings) {
            rating.addRating(rate);
        }

        return rating;
    }
}