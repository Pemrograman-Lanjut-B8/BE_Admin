package id.ac.ui.cs.advprog.admin.service;

import id.ac.ui.cs.advprog.admin.model.UserEntity;
import id.ac.ui.cs.advprog.admin.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        // Given
        List<UserEntity> userList = new ArrayList<>();
        UserEntity user1 = new UserEntity("user1", "user1@example.com", "password123");
        UserEntity user2 = new UserEntity("user2", "user2@example.com", "password456");
        userList.add(user1);
        userList.add(user2);

        // When
        when(userRepository.findAll()).thenReturn(userList);
        List<UserEntity> foundUsers = userService.findAll();

        // Then
        assertEquals(2, foundUsers.size());
        assertEquals(userList, foundUsers);
        verify(userRepository, times(1)).findAll();
    }
}
