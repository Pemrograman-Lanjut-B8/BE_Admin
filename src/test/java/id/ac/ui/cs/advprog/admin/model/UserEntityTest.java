package id.ac.ui.cs.advprog.admin.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {

    private UserEntity user;
    private Role roleUser;
    private Role roleAdmin;

    @BeforeEach
    void setup() {
        roleUser = new Role(ERole.ROLE_USER);
        roleUser.setId(1L);

        roleAdmin = new Role(ERole.ROLE_ADMIN);
        roleAdmin.setId(2L);

        user = new UserEntity("testuser", "test@example.com", "password123");
        user.setId(UUID.randomUUID());
        user.setFullName("Test User");
        user.setPhoneNumber("1234567890");
        user.setProfilePicture("profilePic.jpg");
        user.setBio("This is a test user.");
        user.setGender("Male");
        user.setBirthDate(new Date());

        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        roles.add(roleAdmin);
        user.setRoles(roles);
    }

    @Test
    void testAttributes() {
        assertNotNull(user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("Test User", user.getFullName());
        assertEquals("1234567890", user.getPhoneNumber());
        assertEquals("profilePic.jpg", user.getProfilePicture());
        assertEquals("This is a test user.", user.getBio());
        assertEquals("Male", user.getGender());
        assertNotNull(user.getBirthDate());
    }

    @Test
    void testRoles() {
        assertNotNull(user.getRoles());
        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains(roleUser));
        assertTrue(user.getRoles().contains(roleAdmin));
    }

    @Test
    void testNoArgsConstructor() {
        UserEntity newUser = new UserEntity();
        assertNull(newUser.getId());
        assertNull(newUser.getUsername());
        assertNull(newUser.getEmail());
        assertNull(newUser.getPassword());
        assertNull(newUser.getFullName());
        assertNull(newUser.getPhoneNumber());
        assertNull(newUser.getProfilePicture());
        assertNull(newUser.getBio());
        assertNull(newUser.getGender());
        assertNull(newUser.getBirthDate());
        assertNotNull(newUser.getRoles());
        assertTrue(newUser.getRoles().isEmpty());
    }

    @Test
    void testAllArgsConstructor() {
        UserEntity newUser = new UserEntity("newuser", "newuser@example.com", "newpassword123");

        assertNull(newUser.getId());
        assertEquals("newuser", newUser.getUsername());
        assertEquals("newuser@example.com", newUser.getEmail());
        assertEquals("newpassword123", newUser.getPassword());
        assertNull(newUser.getFullName());
        assertNull(newUser.getPhoneNumber());
        assertNull(newUser.getProfilePicture());
        assertNull(newUser.getBio());
        assertNull(newUser.getGender());
        assertNull(newUser.getBirthDate());
        assertNotNull(newUser.getRoles());
        assertTrue(newUser.getRoles().isEmpty());
    }
}
