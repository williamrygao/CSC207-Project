import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
Google Books API.
 */
public class GoogleBooksApi {
    private final String apiKey;
    private final String volumeID;

    public GoogleBooksApi(String volumeID, String apiKey) {
        this.volumeID = volumeID;
        this.apiKey = apiKey;
    }

    /*
    Given Google Books Volume ID return book data.
     */
    public String getBookByVolumeId(String volumeId) {
        try {
            final String urlString = "https://www.googleapis.com/books/v1/volumes/" + volumeId + "?key=" + apiKey;
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
