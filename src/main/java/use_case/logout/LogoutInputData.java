package use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {

    /**
     * String username.
     */
    private final String username;

    /**
     * LogoutInputData method.
     * @param username the username
     */
    public LogoutInputData(final String username) {
        this.username = username;
    }

    /**
     * Getter for username.
     * @return username
     */
    public String getUsername() {
        return username;
    }
}
