package service;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * This class initializes Firebase for the application.
 */

public class FirebaseInitialize {

    /**
     * Initializes the Firebase app with the provided service account credentials.
     *
     * @throws IOException if the service account file is not found or unreadable.
     */
    public void initialize() throws IOException {
        try (FileInputStream serviceAccount = new FileInputStream("./serviceAccount.json")) {
            final FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://csc207project-ed2f9.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        }
        catch (IOException ex) {
            System.err.println("Error initializing Firebase: " + ex.getMessage());
            // Rethrow to indicate failure
            throw ex;

        }
    }
}
