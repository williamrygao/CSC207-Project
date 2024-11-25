package data_access;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

/**
 * Initializes the Firebase Database.
 */
public class FirebaseInitializer {

    /**
     * This method initializes Firebase and returns a Firestore instance.
     */
    public static Firestore getFirestore() {
        try {
            final FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/serviceAccount.json");

            // Set up FirebaseOptions with credentials from the json file
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://csc207project-ed2f9-default-rtdb.firebaseio.com/")
                    .build();

            // Initialize FirebaseApp only if it hasn't been initialized already
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            // Return the Firestore instance
            return FirestoreClient.getFirestore();
        }
        catch (IOException exception) {
            // Print error and return null if Firebase initialization fails
            exception.printStackTrace();
            return null;
        }
    }
}
