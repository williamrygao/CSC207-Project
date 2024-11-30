package use_case.update_listings;

/**
 * The Input Data for the Login Use Case.
 */
public class UpdateListingsInputData {

    private final String username;

    public UpdateListingsInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
