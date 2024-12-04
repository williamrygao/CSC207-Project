package use_case.update_listings;

/**
 * A data structure that encapsulates the input data required for the Update Listings use case.
 * This class adheres to the Data Transfer Object (DTO) design pattern,
 * facilitating the transfer of information across the application's layers.
 */
public class UpdateListingsInputData {

    private final String username;

    /**
     * Constructs an UpdateListingsInputData object.
     *
     * @param username the username of the user whose listings are to be updated.
     */
    public UpdateListingsInputData(String username) {
        this.username = username;
    }

    /**
     * Retrieves the username associated with this input data.
     *
     * @return the username.
     */
    String getUsername() {
        return username;
    }
}
