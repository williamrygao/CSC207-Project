package data_access;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import entity.Listing;
import entity.User;
import entity.UserFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.add_to_wishlist.AddToWishlistUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.remove_from_wishlist.RemoveFromWishlistUserDataAccessInterface;
import use_case.sell.SellUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.view_wishlist.ViewWishlistUserDataAccessInterface;

/**
 * DAO for user data using Firebase.
 */
public class FirebaseUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        SellUserDataAccessInterface,
        RemoveFromWishlistUserDataAccessInterface,
        AddToWishlistUserDataAccessInterface,
        ViewWishlistUserDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    private final UserFactory userFactory;
    private final OkHttpClient httpClient;
    private final String firebaseBaseUrl;

    /**
     * Constructor for FirebaseUserDataAccessObject.
     *
     * @param userFactory    Factory for creating User objects.
     * @param firebaseBaseUrl Base URL for the Firebase database.
     */
    public FirebaseUserDataAccessObject(final UserFactory userFactory, final String firebaseBaseUrl) {
        this.userFactory = userFactory;
        this.firebaseBaseUrl = firebaseBaseUrl;
        this.httpClient = new OkHttpClient();
    }

    @Override
    public User get(final String username) {
        String url = firebaseBaseUrl + "/users/" + username + ".json";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                JSONObject userJson = new JSONObject(response.body().string());
                String name = userJson.getString("username");
                String password = userJson.getString("password");
                return userFactory.create(name, password);
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException("Error fetching user: " + e.getMessage(), e);
        }

        throw new RuntimeException("User not found");
    }

    @Override
    public void setCurrentUsername(final String name) {
        // This might involve storing the username in a local variable or shared preference if needed.
    }

    @Override
    public boolean existsByName(final String username) {
        String url = firebaseBaseUrl + "/users/" + username + ".json";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.isSuccessful() && response.body() != null && !response.body().string().equals("null");
        } catch (IOException e) {
            throw new RuntimeException("Error checking user existence: " + e.getMessage(), e);
        }
    }

    @Override
    public void save(final User user) {
        String url = firebaseBaseUrl + "/users/" + user.getName() + ".json";
        JSONObject userJson = new JSONObject();
        try {
            userJson.put("username", user.getName());
            userJson.put("password", user.getPassword());

            RequestBody body = RequestBody.create(userJson.toString(), MediaType.parse(CONTENT_TYPE_JSON));
            Request request = new Request.Builder()
                    .url(url)
                    .put(body) // Firebase uses PUT for individual resources
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("Failed to save user: " + response.message());
                }
            }
        } catch (JSONException | IOException e) {
            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
        }
    }

    @Override
    public void changePassword(final User user) {
        save(user);
    }

    @Override
    public void removeFromWishlist(User user, Listing listing) {
        final String url = firebaseBaseUrl + "/users/" + user.getName() + "/wishlist/" + listing.getBook().getBookId() + ".json";
        final Request request = new Request.Builder()
                .url(url)
                .delete()
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to remove listing from wishlist: " + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error removing listing from wishlist: " + e.getMessage(), e);
        }
    }

    @Override
    public void addToWishlist(User user, Listing listing) {
        String url = firebaseBaseUrl + "/users/" + user.getName() + "/wishlist/" + listing.getBook().getBookId() + ".json";
        JSONObject listingJson = new JSONObject();
        try {
            listingJson.put("bookID", listing.getBook().getBookId());
            listingJson.put("price", listing.getPrice());

            RequestBody body = RequestBody.create(listingJson.toString(), MediaType.parse(CONTENT_TYPE_JSON));
            Request request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("Failed to add listing to wishlist: " + response.message());
                }
            }
        }
        catch (JSONException | IOException e) {
            throw new RuntimeException("Error adding listing to wishlist: " + e.getMessage(), e);
        }
    }

    @Override
    public String getCurrentUsername() {
        return null;
    }

    @Override
    public List<Listing> getWishlist(User user) {
        return List.of();
    }
}
