package use_case.logout;

/**
 * DAO for the Logout Use Case.
 */
public interface LogoutUserDataAccessInterface {
    /**
     * Sets the username indicating who is the current user of the application.
     * @param username the new current username
     */
    void setCurrentUsername(String username);
}
