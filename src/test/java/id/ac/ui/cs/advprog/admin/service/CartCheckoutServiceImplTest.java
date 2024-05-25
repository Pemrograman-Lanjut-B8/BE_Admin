package id.ac.ui.cs.advprog.admin.service;

import id.ac.ui.cs.advprog.admin.dto.CartCheckoutAdminDTO;
import id.ac.ui.cs.advprog.admin.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.admin.model.CartCheckout;
import id.ac.ui.cs.advprog.admin.model.UserEntity;
import id.ac.ui.cs.advprog.admin.repository.CartCheckoutRepository;
import id.ac.ui.cs.advprog.admin.repository.LogRepository;
import id.ac.ui.cs.advprog.admin.repository.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartCheckoutServiceImplTest {

    @InjectMocks
    private CartCheckoutServiceImpl cartCheckoutService;

    @Mock
    private CartCheckoutRepository cartCheckoutRepository;

    @Mock
    private UserEntityRepository userEntityRepository;

    @Mock
    private LogRepository logRepository;

    @Mock
    private RestTemplate restTemplate;

    private CartCheckoutDTO[] cartCheckoutDTOs;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        cartCheckoutDTOs = new CartCheckoutDTO[1];
        cartCheckoutDTOs[0] = new CartCheckoutDTO();
        cartCheckoutDTOs[0].setId(1L);
        cartCheckoutDTOs[0].setUserId(UUID.randomUUID().toString());
        cartCheckoutDTOs[0].setItems(new ArrayList<>());
        cartCheckoutDTOs[0].setTotalPrice(100.0);
        cartCheckoutDTOs[0].setStatus("Menunggu Pengiriman Buku");

        userEntity = new UserEntity();
        userEntity.setFullName("User Test");
        userEntity.setEmail("user@test.com");
        userEntity.setPhoneNumber("1234567890");
    }

    @Test
    void testFindAllCartCheckout() {
        String url = "http://localhost:8090/cart/list";
        when(restTemplate.getForEntity(url, CartCheckoutDTO[].class)).thenReturn(new ResponseEntity<>(cartCheckoutDTOs, HttpStatus.OK));
        when(userEntityRepository.findById(any(UUID.class))).thenReturn(Optional.of(userEntity));

        List<CartCheckoutAdminDTO> result = cartCheckoutService.findAllCartCheckout();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("User Test", result.get(0).getNamaUser());
        assertEquals("user@test.com", result.get(0).getEmailUser());
        assertEquals("1234567890", result.get(0).getPhoneNumberUser());
        assertEquals(100.0, result.get(0).getTotalPrice());
        assertEquals("Menunggu Pengiriman Buku", result.get(0).getStatus());
    }

    @Test
    void testFindCartCheckoutById() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);
        when(cartCheckoutRepository.findCartCheckoutById(1L)).thenReturn(cartCheckout);

        CartCheckout result = cartCheckoutService.findCartCheckoutById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindCartCheckoutByEmail() {
        CartCheckout cartCheckout = new CartCheckout();
        List<CartCheckout> cartCheckoutList = List.of(cartCheckout);
        when(userEntityRepository.findUserEntityByEmail("user@test.com")).thenReturn(userEntity);
        when(cartCheckoutRepository.findCartCheckoutByUser(userEntity)).thenReturn(cartCheckoutList);

        List<CartCheckout> result = cartCheckoutService.findCartCheckoutByEmail("user@test.com");

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateStatus() {
        CartCheckout cartCheckout = new CartCheckout();
        cartCheckout.setId(1L);
        cartCheckout.setStatus("Menunggu Pengiriman Buku");
        when(cartCheckoutRepository.findCartCheckoutById(1L)).thenReturn(cartCheckout);

        cartCheckoutService.updateStatus(1L);

        assertEquals("Dalam Pengiriman", cartCheckout.getStatus());
        verify(cartCheckoutRepository, times(1)).save(cartCheckout);
    }
}
