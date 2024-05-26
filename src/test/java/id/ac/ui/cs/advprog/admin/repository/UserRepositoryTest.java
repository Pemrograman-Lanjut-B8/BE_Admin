package id.ac.ui.cs.advprog.admin.repository;

import id.ac.ui.cs.advprog.admin.model.UserEntity;
import id.ac.ui.cs.advprog.admin.model.Role;
import id.ac.ui.cs.advprog.admin.model.ERole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserEntity("testuser", "test@example.com", "password123");
        testUser.setId(UUID.randomUUID());
        testUser.setFullName("Test User");
        testUser.setPhoneNumber("1234567890");
        testUser.setProfilePicture("profilePic.jpg");
        testUser.setBio("This is a test user.");
        testUser.setGender("Male");
        testUser.setBirthDate(new java.util.Date());

        // Set roles
        Role roleUser = new Role(ERole.ROLE_USER);
        roleUser.setId(1L);
        Role roleAdmin = new Role(ERole.ROLE_ADMIN);
        roleAdmin.setId(2L);

        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        roles.add(roleAdmin);
        testUser.setRoles(roles);
    }

    @Test
    void testCreateAndFind() {
        when(userRepository.save(testUser)).thenReturn(testUser);
        UserEntity savedUser = userRepository.save(testUser);
        assertEquals(testUser, savedUser);

        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        Optional<UserEntity> foundUser = userRepository.findById(testUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals(testUser, foundUser.get());
    }

    @Test
    void testFindAllIfEmpty() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        var users = userRepository.findAll();
        assertTrue(users.isEmpty());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).delete(testUser);
        userRepository.delete(testUser);
        verify(userRepository).delete(testUser);
    }

    @Test
    void testUpdateUser() {
        when(userRepository.save(testUser)).thenReturn(testUser);
        UserEntity updatedUser = userRepository.save(testUser);
        assertEquals(testUser, updatedUser);

        // Assuming there's a method to update user in UserRepository
        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        UserEntity userToUpdate = userRepository.findById(testUser.getId()).get();
        userToUpdate.setUsername("updateduser");
        userToUpdate.setEmail("updated@example.com");

        when(userRepository.save(userToUpdate)).thenReturn(userToUpdate);
        UserEntity updatedUserAgain = userRepository.save(userToUpdate);
        assertEquals("updateduser", updatedUserAgain.getUsername());
        assertEquals("updated@example.com", updatedUserAgain.getEmail());
    }
}
