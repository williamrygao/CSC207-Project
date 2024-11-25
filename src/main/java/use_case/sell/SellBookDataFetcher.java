package use_case.sell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;

import data_access.GoogleBooksApi;
import entity.Book;
import entity.BookFactory;
import entity.Listing;

/**
 * SellBookDataFetcher Fetches Book Selling Data.
 */
public class SellBookDataFetcher implements SellBookDataAccessInterface {
    private static final String API_KEY = GoogleBooksApi.loadApiKey();

    @Override
    public boolean existsByBookID(String bookID) {
        return false;
    }

    @Override
    public void save(Book book) {
    }

    @Override
    public void save(Listing listing) {

    }

    @Override
    public String getBookPrice(String bookID) {
        String message = "";
        final int httpSuccess = 200;
        try {
            // Construct the URL for the Google Books API request
            final String urlString = "https://www.googleapis.com/books/v1/volumes/" + bookID + "?key=" + API_KEY;
            final URL url = new URL(urlString);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Check if the response code has passed (200 not 404 or something else)
            if (conn.getResponseCode() != httpSuccess) {
                throw new RuntimeException("Failed: HTTP error code : " + conn.getResponseCode());
            }

            // Read the API response into a StringBuilder
            final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            conn.disconnect();

            // Parse the JSON response
            final JSONObject jsonResponse = new JSONObject(response.toString());
            final JSONObject saleInfo = jsonResponse.optJSONObject("saleInfo");

            // Check if price information is available
            if (saleInfo != null && saleInfo.has("retailPrice")) {
                final JSONObject retailPrice = saleInfo.getJSONObject("retailPrice");
                final double price = retailPrice.getDouble("amount");
                final String currency = retailPrice.getString("currencyCode");

                // Format the message with the price and currency code
                message = String.format("Price: %.2f %s", price, currency);
            }
            else {
                message = "Price information not available.";
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
            message = "Error retrieving price information.";
        }
        return message;
    }

    @Override
    public String getUserSellingListing(String SellingPrice, String bookID, String userID) {
        final Book book = BookFactory.createBook(bookID);
        final String title = book.getTitle();
        final List<String> authors = book.getAuthors();
        String authorMessage = "";
        String returnMessage = "";

        if (authors.size() > 2) {
            final String author1 = authors.get(0);
            final String author2 = authors.get(1);
            authorMessage = author1 + ", " + author2 + ", et al.";
        }
        else if (authors.size() == 2) {
            final String author1 = authors.get(0);
            final String author2 = authors.get(1);
            authorMessage = author1 + " and " + author2;
        }
        else {
            final String author1 = authors.get(0);
            authorMessage = author1;
        }

        if (SellingPrice == null || SellingPrice.isEmpty()) {
            returnMessage = "Error, please enter a valid price to list your book at on Book Marketplace.";
        }

        else {
            returnMessage = "Hello " + userID + ", \nThank you for listing your book with us! We've"
                + " successfully updated your listing for the book titled: " + "'" + title + "'"
                + " by " + authorMessage + " with your preferred price of: " + SellingPrice + "."
                + " \nOther users can now view your book at this price. You will be notified if"
                + " anyone expresses interest or makes a purchase. If you'd like to edit your listing"
                + " at any time, you can do so through your account.\nIf you have any questions or"
                + " need assistance, don't hesitate to reach out. We're here to help\nBest regards";
        }

        return returnMessage;
    }
}
