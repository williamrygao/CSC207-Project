package data_access;

import entity.listing.Listing;
import entity.user.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for creating and saving a user in Firebase using the FirebaseUserDataAccessObject.
 * This test focuses on saving a user object into Firebase.
 */
public class UserCreationTest {

    private FirebaseUserDataAccessObject mockUserRepository;

    /**
     * The DAO (Data Access Object) used to interact with Firebase for saving user data.
     */
    @BeforeEach
    public void setUp() {
        // Initialize mock of firebase
        mockUserRepository = mock(FirebaseUserDataAccessObject.class);
    }

    /**
     * Test method for saving a user into Firebase.
     * This method will create a user, save it using the DAO, and verify the operation.
     */
    @Test
    public void testSaveUser() {
        // Arrange
        final String userName = "testUser";
        final String password = "password123";

        final User user = new User() {
            @Override
            public String getName() {
                return userName;
            }

            @Override
            public String getPassword() {
                return password;
            }

            @Override
            public List<Listing> getWishlist() {
                // Return an empty list for the wishlist
                return Collections.emptyList();
            }
        };

        doNothing().when(mockUserRepository).save(user); // simulate saving user
        when(mockUserRepository.get(userName)).thenReturn(user); // simulate retrieving the saved user

        // Act: Call save on the DAO
        mockUserRepository.save(user);

        // Assert: Verify if the user is saved in the mock Firebase database
        User savedUser = mockUserRepository.get(userName);
        assertEquals(userName, savedUser.getName(), "User name should match");
        assertEquals(password, savedUser.getPassword(), "User password should match");

        // Verify that the save method was called once
        verify(mockUserRepository, times(1)).save(user);
        verify(mockUserRepository, times(1)).get(userName);
    }
}
