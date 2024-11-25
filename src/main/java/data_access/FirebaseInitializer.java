package data_access;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {

    private static Firestore db;

    // Initialize Firebase
    public static void initializeFirebase() throws IOException {
        if (db == null) {
            // Load the service account key from the file path (ensure correct path)
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/csc207-d5985-firebase-adminsdk-vpvh9-e9b2410af1.json");

            // Initialize Firebase options using the credentials
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
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
