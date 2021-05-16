package gr.aueb.team14.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testName", "testing321");
    }

    @Test
    void setUsername() {
        assertEquals("testName", user.getUsername());
    }

    @Test
    void setPassword() {
        assertEquals(40, user.getPassword().length());
    }

    @Test
    void checkPasswords() {
        assertTrue(User.checkPasswords("testing321", "testing321"));
        assertFalse(User.checkPasswords("testing321", "testingDifferent"));
    }
}