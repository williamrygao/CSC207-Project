import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
Google Books API.
 */
public class GoogleBooksApi {
    private static final String API_KEY = "AIzaSyDtYWsQfS0HPcdpKrafrmCjZEGNZm9T_So";
    private final String volumeID;

    public GoogleBooksAPI(String volumeID) {
        this.volumeID = volumeID;
    }

    public GoogleBooksAPI() {
        this.volumeID = "9xHCAgAAQBAJ";
    }

    public static String getBookByVolumeId(String volumeId) {
        try {
            final String urlString = "https://www.googleapis.com/books/v1/volumes/" + volumeId + "?key=" + API_KEY;
            final URL url = new URL(urlString);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
