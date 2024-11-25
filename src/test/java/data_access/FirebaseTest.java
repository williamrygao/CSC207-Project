package data_access;

public class FirebaseTest {

    public static void main(String[] args) {
        try {
            // Initialize Firebase from the FirebaseInitializer
            FirebaseInitializer.initializeFirebase();
        } catch (Exception e) {
            // Print the error message if something goes wrong
            e.printStackTrace();
        }
    }
}
