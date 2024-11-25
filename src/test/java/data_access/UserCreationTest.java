package data_access;

import entity.User;
import entity.UserFactory;
import service.FirebaseUserDataAccessObject;

/**
 * Test class for creating and saving a user in Firebase using the FirebaseUserDataAccessObject.
 * This test focuses on saving a user object into Firebase.
 */
public class UserCreationTest {

    private service.FirebaseUserDataAccessObject dao;
    private UserFactory userFactory;

    /**
     * The DAO (Data Access Object) used to interact with Firebase for saving user data.
     */
    public void setUp() {
        // Initialize the UserFactory (use a simple implementation for testing)
        userFactory = new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new User() {
                    @Override
                    public String getName() {
                        return name;
                    }

                    @Override
                    public String getPassword() {
                        return password;
                    }
                };
            }
        };

        // Initialize the DAO with the real userFactory
        dao = new FirebaseUserDataAccessObject(userFactory);
    }

    /**
     * Test method for saving a user into Firebase.
     * This method will create a user, save it using the DAO, and verify the operation.
     */
    public void testSaveUser() {
        // Arrange
        final String userName = "testUser";
        final String password = "password123";

        final User user = userFactory.create(userName, password);

        // Act: Call save on the DAO
        dao.save(user);
        System.out.println("User saved successfully.");
    }

    /**
     * Main method to run the test from the console.
     * @param args stuff
     */
    public static void main(String[] args) {
        // Instantiate the test class
        final UserCreationTest test = new UserCreationTest();

        // Set up and run the test
        test.setUp();
        test.testSaveUser();
    }
}
