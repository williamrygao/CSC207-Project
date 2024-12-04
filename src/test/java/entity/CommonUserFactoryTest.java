package entity.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommonUserFactoryTest {

    @Test
    void testCreate() {
        UserFactory factory = new CommonUserFactory();

        // Test user creation
        String name = "John Doe";
        String password = "password123";

        User user = factory.create(name, password);

        // Check if the created user is an instance of CommonUser
        assertTrue(user instanceof CommonUser);

        // Check the values of name and password
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }
}
