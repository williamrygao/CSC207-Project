package data_access;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

/**
 * Google Books API.
 */
public class GoogleBooksApi {
    private static final int HTTP_OK = 200;
    private final String apiKey;

    public GoogleBooksApi() {
        this.apiKey = loadApiKey();
    }

    /**
     * Load API Key from api_key.env file.
     * @return ApiKey
     * @throws RuntimeException runtime exception
     */
    public static String loadApiKey() throws RuntimeException {
        try (BufferedReader reader = new BufferedReader(new FileReader("api_key.env"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("API_KEY")) {
                    return line.split("=")[1].trim().replaceAll("\"", "");
                }
            }
            throw new RuntimeException("API key not found in api_key.env file.");
        }
        catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error reading api_key.env file", exception);
        }
    }

    /**
     * Given Google Books Volume ID, return book data.
     * @param volumeId the volumeId
     * @return a json file containing all information returned from Google API
     */
    public String getBookByVolumeId(String volumeId) {
        String result = "";
        try {
            final String urlString = "https://www.googleapis.com/books/v1/volumes/" + volumeId + "?key=" + apiKey;
            final URL url = new URL(urlString);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HTTP_OK) {
                throw new RuntimeException("Failed: HTTP error code : " + conn.getResponseCode());
            }

            final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            conn.disconnect();

            result = response.toString();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            result = null;
        }
        return result;
    }

    /**
     * Get book price.
     * @param volumeID volumeID
     * @return price
     */
    public String getBookPrice(String volumeID) {
        String message = "";
        final int httpSuccess = 200;
        try {
            // Construct the URL for the Google Books API request
            final String urlString = "https://www.googleapis.com/books/v1/volumes/" + volumeID + "?key=" + apiKey;
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
