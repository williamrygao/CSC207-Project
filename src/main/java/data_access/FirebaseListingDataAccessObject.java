package data_access;

import entity.BookFactory;
import entity.Listing;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.login.LoginListingDataAccessInterface;
import use_case.sell.SellBookDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The DAO for book data.
 */
public class FirebaseListingDataAccessObject implements SellBookDataAccessInterface, LoginListingDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    private final BookFactory bookFactory;
    private final OkHttpClient httpClient;
    private final String firebaseBaseUrl;

    /**
     * FirebaseListingDataAccessObject constructor.
     *
     * @param bookFactory   Factory for creating Book objects.
     * @param firebaseBaseUrl Base URL for the Firebase database.
     */
    public FirebaseBookDataAccessObject(final BookFactory bookFactory, final String firebaseBaseUrl) {
        this.bookFactory = bookFactory;
        this.firebaseBaseUrl = firebaseBaseUrl;
        this.httpClient = new OkHttpClient();
    }

    @Override
    public boolean existsByBookID(String bookID) {
        String url = firebaseBaseUrl + "/books/" + bookID + ".json";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() == SUCCESS_CODE) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                return jsonResponse.length() > 0;
            }
        }
        catch (IOException | JSONException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(final Listing listing) {
        String url = firebaseBaseUrl + "/listings.json";
        JSONObject jsonListing = new JSONObject();
        try {
            jsonListing.put("bookID", listing.getBook().getBookId());
            jsonListing.put("price", listing.getPrice());
            jsonListing.put("sellerUsername", listing.getSeller());

            RequestBody body = RequestBody.create(
                    jsonListing.toString(), MediaType.parse(CONTENT_TYPE_JSON));

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (response.code() != SUCCESS_CODE) {
                    System.out.println("Failed to save listing: " + response.message());
                }
            }
        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBookPrice(String bookID) {
        String url = firebaseBaseUrl + "/listings.json?orderBy=\"bookID\"&equalTo=\"" + bookID + "\"";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() == SUCCESS_CODE) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                if (jsonResponse.keys().hasNext()) {
                    String firstKey = jsonResponse.keys().next();
                    JSONObject listing = jsonResponse.getJSONObject(firstKey);
                    return listing.getString("price");
                }
            }
        }
        catch (IOException | JSONException exception) {
            exception.printStackTrace();
        }
        return "";
    }

    @Override
    public String getUserSellingListing(String SellingPrice, String bookID) {
        return "";
    }

    /**
     * Retrieves all listings from the Firebase database.
     *
     * @return a list of listings or an empty list if none exist or an error occurs.
     */
    public List<Listing> getListings() {
        String url = firebaseBaseUrl + "/listings.json";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        List<Listing> listings = new ArrayList<>();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() == SUCCESS_CODE) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);

                // Iterate over JSON keys to extract listings
                Iterator<String> keys = jsonResponse.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    JSONObject jsonListing = jsonResponse.getJSONObject(key);

                    // Extract attributes from JSON and create a Listing
                    String bookID = jsonListing.getString("bookID");
                    double price = jsonListing.getDouble("price");
                    String sellerUsername = jsonListing.getString("sellerUsername");

                    // Create the Listing object using the BookFactory and extracted attributes
                    listings.add(new Listing(
                            bookID,
                            bookFactory.createBook(bookID),
                            price,
                            sellerUsername,
                            true
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

        return listings;
    }
}
