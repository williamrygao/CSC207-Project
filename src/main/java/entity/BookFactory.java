package entity;

import org.json.JSONArray;
import org.json.JSONObject;

import data_access.GoogleBooksApi;

/**
 * Factory for creating Book objects.
 */
public class BookFactory {
    public static void main(String[] args) {
        final BookFactory bookFactory = new BookFactory();
        bookFactory.createBook("9xHCAgAAQBAJ");
    }
    /**
     * Create a Book.
     * @param volumeId Google Books API identifier
     * @return new Book object
     */
    public Book createBook(String volumeId) {
        final String jsonResponse = GoogleBooksApi.getBookByVolumeId(volumeId);
        if (jsonResponse != null) {
            // Parse the JSON response to extract the book details
            final JSONObject bookJson = new JSONObject(jsonResponse);
            final JSONObject volumeInfo = bookJson.getJSONObject("volumeInfo");

            final String title = volumeInfo.optString("title", "Unknown Title");
            final String author = volumeInfo.optString("authors", "Unknown Author");
            final String description = volumeInfo.optString("description", "No description available");
            final String genre = extractGenre(volumeInfo);

            // Create and return a new Book object using the retrieved data
            final Book book = new Book(volumeId, title, author, description, genre);
            System.out.println(book);
            return book;
        }
        return null;
    }

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
