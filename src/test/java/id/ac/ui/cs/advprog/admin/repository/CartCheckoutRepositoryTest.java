//package id.ac.ui.cs.advprog.admin.repository;
//
//import id.ac.ui.cs.advprog.admin.model.CartCheckout;
//import id.ac.ui.cs.advprog.admin.model.UserEntity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//class CartCheckoutRepositoryTest {
//
//    @Autowired
//    private CartCheckoutRepository cartCheckoutRepository;
//
//    @Autowired
//    private UserEntityRepository userEntityRepository;
//
//    private UserEntity user;
//
//    @BeforeEach
//    void setUp() {
//        user = new UserEntity();
//        user.setId(UUID.randomUUID());
//        user.setFullName("Test User");
//        user.setEmail("testuser@example.com");
//        user.setPhoneNumber("1234567890");
//        userEntityRepository.save(user);
//
//        CartCheckout cartCheckout1 = new CartCheckout();
//        cartCheckout1.setUser(user);
//        cartCheckout1.setStatus("Menunggu Pengiriman Buku");
//        cartCheckoutRepository.save(cartCheckout1);
//
//        CartCheckout cartCheckout2 = new CartCheckout();
//        cartCheckout2.setUser(user);
//        cartCheckout2.setStatus("Dalam Pengiriman");
//        cartCheckoutRepository.save(cartCheckout2);
//    }
//
//    @Test
//    void testFindCartCheckoutById() {
//        CartCheckout cartCheckout = new CartCheckout();
//        cartCheckout.setUser(user);
//        cartCheckout.setStatus("Pengiriman Selesai");
//        cartCheckout = cartCheckoutRepository.save(cartCheckout);
//
//        CartCheckout foundCartCheckout = cartCheckoutRepository.findCartCheckoutById(cartCheckout.getId());
//
//        assertNotNull(foundCartCheckout);
//        assertEquals(cartCheckout.getId(), foundCartCheckout.getId());
//    }
//
//    @Test
//    void testFindCartCheckoutByUser() {
//        List<CartCheckout> foundCartCheckouts = cartCheckoutRepository.findCartCheckoutByUser(user);
//
//        assertNotNull(foundCartCheckouts);
//        assertEquals(2, foundCartCheckouts.size());
//        assertEquals(user.getId(), foundCartCheckouts.get(0).getUser().getId());
//        assertEquals(user.getId(), foundCartCheckouts.get(1).getUser().getId());
//    }
//}
