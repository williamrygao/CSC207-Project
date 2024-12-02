package entity.book;

import org.json.JSONArray;
import org.json.JSONObject;

import data_access.GoogleBooksApi;

/**
 * Factory for creating Book objects from data retrieved via the Google Books API.
 */

public class BookFactory {
    private final GoogleBooksApi googleBooksApi;

    public BookFactory() {
        this.googleBooksApi = new GoogleBooksApi();
    }

    /**
     * Create a Book.
     * @param volumeID Google Books API identifier
     * @return new Book object
     */

    public Book createBook(String volumeID) {
        final String jsonResponse = googleBooksApi.getBookByVolumeId(volumeID);
        if (jsonResponse != null) {
            // Parse the JSON response to extract the book details
            final JSONObject bookJson = new JSONObject(jsonResponse);
            final JSONObject volumeInfo = bookJson.getJSONObject("volumeInfo");

            final String title = volumeInfo.optString("title", "Unknown Title");
            final JSONArray authorsArray = volumeInfo.optJSONArray("authors");
            final String authors;
            if (authorsArray != null) {
                authors = String.join(", ", authorsArray.toList().stream()
                        .map(Object::toString)
                        .toArray(String[]::new));
            }
            else {
                authors = "Unknown Author";
            }
            final String description = volumeInfo.optString("description", "No description available");
            final String genre = extractGenre(volumeInfo);

            String bookPrice = "Price information not available.";
            final JSONObject saleInfo = bookJson.optJSONObject("saleInfo");

            // Check if price information is available
            if (saleInfo != null && saleInfo.has("retailPrice")) {
                final JSONObject retailPrice = saleInfo.getJSONObject("retailPrice");
                final double price = retailPrice.getDouble("amount");
                final String currency = retailPrice.getString("currencyCode");

                // Format the message with the price and currency code
                bookPrice = String.format("Price: %.2f %s", price, currency);
            }

            // Create and return a new Book object using the retrieved data
            return new Book(volumeID, title, authors, genre, bookPrice);
        }
        return null;
    }

    /**
     * Extracts the genre(s) from the volume's JSON data.
     *
     * @param volumeInfo the JSON object containing volume details
     * @return a comma-separated string of genres, or "Unknown Genre" if none are found
     */
    private String extractGenre(JSONObject volumeInfo) {
        // Check if the 'categories' field exists and is not empty
        final StringBuilder genre = new StringBuilder();
        if (volumeInfo.has("categories")) {
            final JSONArray categories = volumeInfo.getJSONArray("categories");

            for (int i = 0; i < categories.length(); i++) {
                // Append each category to the genres StringBuilder
                genre.append(categories.getString(i));

                // Add a comma between genres if it's not the last one
                if (i < categories.length() - 1) {
                    genre.append(", ");
                }
            }
        }
        if (genre.length() > 0) {
            return genre.toString();
        }
        else {
            return "Unknown Genre";
        }
    }
}
