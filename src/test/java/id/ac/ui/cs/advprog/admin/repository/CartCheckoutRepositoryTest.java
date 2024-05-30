package id.ac.ui.cs.advprog.admin.repository;

import id.ac.ui.cs.advprog.admin.model.CartCheckout;
import id.ac.ui.cs.advprog.admin.model.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CartCheckoutRepositoryTest {

    @Mock
    private CartCheckoutRepository cartCheckoutRepository;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindCartCheckoutById() {

        Long id = 1L;
        CartCheckout dummyCartCheckout = new CartCheckout();

        when(cartCheckoutRepository.findCartCheckoutById(id)).thenReturn(dummyCartCheckout);

        CartCheckout result = cartCheckoutRepository.findCartCheckoutById(id);

        assertEquals(dummyCartCheckout, result);

    }

    @Test
    void testFindCartCheckoutByUser() {

        UserEntity user = new UserEntity();

        List<CartCheckout> dummyCartCheckoutList = new ArrayList<>();

        when(cartCheckoutRepository.findCartCheckoutByUser(user)).thenReturn(dummyCartCheckoutList);

        List<CartCheckout> result = cartCheckoutRepository.findCartCheckoutByUser(user);

        assertEquals(dummyCartCheckoutList.size(), result.size());

    }
}
