package id.ac.ui.cs.advprog.admin.repository;

import id.ac.ui.cs.advprog.admin.model.CartCheckout;
import id.ac.ui.cs.advprog.admin.model.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CartCheckoutRepositoryTest {

    @Mock
    private CartCheckoutRepository cartCheckoutRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindCartCheckoutById() {
        // Mock data
        Long id = 1L;
        CartCheckout dummyCartCheckout = new CartCheckout();
        // Set dummy CartCheckout properties

        // Mock repository method
        when(cartCheckoutRepository.findCartCheckoutById(id)).thenReturn(dummyCartCheckout);

        // Call the repository method
        CartCheckout result = cartCheckoutRepository.findCartCheckoutById(id);

        // Assertions
        assertEquals(dummyCartCheckout, result);
        // Add more assertions if needed
    }

    @Test
    void testFindCartCheckoutByUser() {
        // Mock data
        UserEntity user = new UserEntity();
        // Set dummy UserEntity properties

        List<CartCheckout> dummyCartCheckoutList = new ArrayList<>();
        // Add dummy CartCheckout objects to the list

        // Mock repository method
        when(cartCheckoutRepository.findCartCheckoutByUser(user)).thenReturn(dummyCartCheckoutList);

        // Call the repository method
        List<CartCheckout> result = cartCheckoutRepository.findCartCheckoutByUser(user);

        // Assertions
        assertEquals(dummyCartCheckoutList.size(), result.size());
        // Add more assertions if needed
    }
}
