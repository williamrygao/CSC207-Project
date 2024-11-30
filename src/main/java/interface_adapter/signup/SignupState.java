package interface_adapter.signup;

/**
 * The state for the Signup View Model.
 */
public class SignupState {
    /**
     * String username = "".
     */
    private String username = "";
    /**
     * String error.
     */
    private String signupError;
    /**
     * String password = "".
     */
    private String password = "";
    /**
     * String passwordError.
     */
    private String passwordError;
    /**
     * String repeatPassword = "".
     */
    private String repeatPassword = "";
    /**
     * String repeatPasswordError.
     */
    private String repeatPasswordError;

    /**
     * Getter for Username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for signup error.
     * @return signup error
     */
    public String getSignupError() {
        return signupError;
    }

    /**
     * Getter for Password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for PasswordError.
     * @return PasswordError
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Getter for RepeatPassword.
     * @return RepeatPassword
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Getter for RepeatPasswordError.
     * @return RepeatPasswordError
     */
    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    /**
     * Setter for Username.
     * @param username the username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Setter for signup error.
     * @param signupError the signup error
     */
    public void setSignupError(final String signupError) {
        this.signupError = signupError;
    }

    /**
     * Setter for password.
     * @param password the password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Sets the password error for the signup process.
     * @param passwordError the password that should match a chosen error
     */
    public void setPasswordError(final String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * Sets the repeat password for the signup process.
     * @param repeatPassword the password that should match original password
     */
    public void setRepeatPassword(final String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * The setRepeatPasswordError method.
     * @param repeatPasswordError the string for repeat password errors
     */
    public void setRepeatPasswordError(final String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    /**
     * Override toString method.
     * @return correct string
     */
    @Override
    public String toString() {
        return "SignupState{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", repeatPassword='" + repeatPassword + '\''
                + '}';
    }
}
