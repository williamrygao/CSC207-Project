package data_access;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

/**
 * Initializes Firebase services (Firestore and Realtime Database).
 */
public class FirebaseInitializer {

    /**
     * Initializes Firebase for both Firestore and Realtime Database.
     * @return The Firestore instance.
     */
    public static Firestore initializeFirebase() {
        try {
            final FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/serviceAccount.json");

            // Set up FirebaseOptions with credentials from the json file
            final FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://csc207project-ed2f9-default-rtdb.firebaseio.com/")
                    .build();

            // Initialize FirebaseApp only if it hasn't been initialized already
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            // Return the Firestore instance (you can also return the Realtime Database instance)
            return FirestoreClient.getFirestore(); // Use this for Firestore
            // Alternatively, for Realtime Database you can return DatabaseReference if needed.
        }
        catch (IOException exception) {
            // Print error and return null if Firebase initialization fails
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Initializes Firebase for Realtime Database and returns a DatabaseReference instance.
     * @return The DatabaseReference instance for Realtime Database.
     */
    public static DatabaseReference getRealtimeDatabaseReference() {
        try {
            final FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/serviceAccount.json");

            final FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://csc207project-ed2f9-default-rtdb.firebaseio.com/")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            // Return the Realtime Database instance
            return FirebaseDatabase.getInstance().getReference();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
