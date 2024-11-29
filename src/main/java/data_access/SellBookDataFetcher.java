package data_access;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import entity.Listing;
import use_case.sell.SellBookDataAccessInterface;

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
}
