package data_access;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {

    // This method initializes Firebase and returns a Firestore instance
    public static Firestore getFirestore() {
        try {
            // Load the google-services.json from the resources folder
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/google-services.json");

            // Set up FirebaseOptions with credentials from the json file
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            // Initialize FirebaseApp only if it hasn't been initialized already
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);  // Initialize Firebase App
            }

            // Return the Firestore instance
            return FirestoreClient.getFirestore();
        } catch (IOException e) {
            // Print error and return null if Firebase initialization fails
            e.printStackTrace();
            return null;
        }
    }
}