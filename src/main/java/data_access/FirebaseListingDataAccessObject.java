package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import entity.Listing;
import entity.book.Book;
import entity.book.BookFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.filter_by_genre.FilterByGenreDataAccessInterface;
import use_case.sell.SellListingDataAccessInterface;
import use_case.update_listings.UpdateListingsListingDataAccessInterface;

/**
 * The DAO for book data.
 */
public class FirebaseListingDataAccessObject implements SellListingDataAccessInterface,
        UpdateListingsListingDataAccessInterface, FilterByGenreDataAccessInterface {
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
    public FirebaseListingDataAccessObject(final BookFactory bookFactory, final String firebaseBaseUrl) {
        this.bookFactory = bookFactory;
        this.firebaseBaseUrl = firebaseBaseUrl;
        this.httpClient = new OkHttpClient();
    }

    @Override
    public boolean exists(String bookID, String seller) {
        // Construct the URL to query listings in the Firebase Realtime Database
        final String url = firebaseBaseUrl + "/listings.json";

        // Create a GET request to fetch all listings from Firebase
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            // Check if the response is successful and contains a body
            if (response.isSuccessful() && response.body() != null) {
                final String responseBody = response.body().string();

                // Handle the case where the response is empty
                if ("null".equals(responseBody) || responseBody.isEmpty()) {
                    return false;
                }

                // Convert the response body to a JSONObject
                final JSONObject listingsJson = new JSONObject(responseBody);

                // Iterate over the listings to check if any match the bookID and seller
                for (String key : listingsJson.keySet()) {
                    final JSONObject listingJson = listingsJson.getJSONObject(key);

                    final String listingBookID = listingJson.optString("bookID");
                    final String listingSeller = listingJson.optString("seller");

                    // Check if this listing matches the provided bookID and seller
                    if (bookID.equals(listingBookID) && seller.equals(listingSeller)) {
                        return true;
                    }
                }

                return false;
            }
            else {
                throw new RuntimeException("Failed to fetch listings: " + response.message());
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException("Error checking for listing: " + e.getMessage(), e);
        }
    }

    @Override
    public void save(final Listing listing) {
        final String url = firebaseBaseUrl + "/listings.json";
        final JSONObject jsonListing = new JSONObject();
        try {
            jsonListing.put("bookID", listing.getBook().getBookId());
            jsonListing.put("title", listing.getBook().getTitle());
            jsonListing.put("authors", listing.getBook().getAuthors());
            jsonListing.put("genre", listing.getBook().getGenre());
            jsonListing.put("bookPrice", listing.getBook().getPrice());
            jsonListing.put("listingPrice", listing.getPrice());
            jsonListing.put("seller", listing.getSeller());
            jsonListing.put("rating", listing.getBook().getRating());
            jsonListing.put("isAvailable", true);

            final RequestBody body = RequestBody.create(
                    jsonListing.toString(), MediaType.parse(CONTENT_TYPE_JSON));

            final Request request = new Request.Builder()
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

    /**
     * Retrieves all listings from the Firebase database.
     *
     * @return a list of listings or an empty list if none exist or an error occurs.
     */
    public List<Listing> getListings() {
        final String url = firebaseBaseUrl + "/listings.json";
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        final List<Listing> listings = new ArrayList<>();

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
                    listings.add(new Listing(
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

        return listings;
    }

    /**
     * Retrieves listings that match the given genre search term (case-insensitive, partial matches allowed).
     *
     * @param genre the genre search term
     * @return a list of listings that match the genre
     */

    @Override
    public List<Listing> getListingsByGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            return new ArrayList<>();
        }

        final String searchGenre = genre.toLowerCase().trim();
        final List<Listing> allListings = getListings();
        if (allListings == null) {
            return new ArrayList<>();
        }

        final List<Listing> filteredListings = new ArrayList<>();
        for (Listing listing : allListings) {
            if (listing == null || listing.getBook() == null || listing.getBook().getGenre() == null) {
                continue;
            }

            final String bookGenre = listing.getBook().getGenre().toLowerCase();
            if (bookGenre.contains(searchGenre)) {
                filteredListings.add(listing);
            }
        }
        return filteredListings;
    }
}
