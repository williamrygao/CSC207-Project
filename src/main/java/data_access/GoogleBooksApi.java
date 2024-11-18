package data_access;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Google Books API.
 */
public class GoogleBooksApi {
    public static final String API_KEY = loadApiKey();
    private static final int HTTP_OK = 200;

    /**
     * Test.
     * @param args the arguments
     */
    public static void main(String[] args) {
        System.out.println(getBookByVolumeId("9xHCAgAAQBAJ"));
    }

    /*
     * Load API Key from api_key.env file.
     * @return ApiKey
     */
    public static String loadApiKey() {
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
    public static String getBookByVolumeId(String volumeId) {
        try {
            final String urlString = "https://www.googleapis.com/books/v1/volumes/" + volumeId + "?key=" + API_KEY;
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

            return response.toString();
        }

        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
