package data_access;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {
    /**
     * This method initializes Firebase and returns a Firestore instance.
     */
    public static Firestore getFirestore() {
        try {
            final FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/serviceAccount.json");
            // Initialize Firebase options using the credentials
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://csc207project-ed2f9-default-rtdb.firebaseio.com/")
                    .build();

            // Initialize Firebase app with the options
            FirebaseApp.initializeApp(options);

            // Get Firestore instance
            db = FirestoreClient.getFirestore();

            System.out.println("Firebase initialized successfully.");
        }
    }

    // Main method to test Firebase initialization independently
    public static void main(String[] args) {
        try {
            initializeFirebase();  // Initialize Firebase
        } catch (IOException e) {
            e.printStackTrace();  // Print any error
        }
    }
}
