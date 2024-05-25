//package id.ac.ui.cs.advprog.admin.repository;
//
//import id.ac.ui.cs.advprog.admin.model.CartCheckout;
//import id.ac.ui.cs.advprog.admin.model.LogAdmin;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//class LogRepositoryTest {
//
//    @Autowired
//    private LogRepository logRepository;
//
//    @Autowired
//    private CartCheckoutRepository cartCheckoutRepository;
//
//    private CartCheckout cartCheckout;
//
//    @BeforeEach
//    void setUp() {
//        cartCheckout = new CartCheckout();
//        cartCheckout.setStatus("Menunggu Pengiriman Buku");
//        cartCheckout = cartCheckoutRepository.save(cartCheckout);
//
//        LogAdmin log1 = new LogAdmin("Status changed to Menunggu Pengiriman Buku", cartCheckout);
//        log1.setDate(LocalDateTime.of(2024, 1, 1, 10, 0));
//        logRepository.save(log1);
//
//        LogAdmin log2 = new LogAdmin("Status changed to Dalam Pengiriman", cartCheckout);
//        log2.setDate(LocalDateTime.of(2024, 1, 2, 12, 0));
//        logRepository.save(log2);
//    }
//
//    @Test
//    void testFindAllByCartCheckoutOrderByDateDesc() throws Exception {
//        CompletableFuture<List<LogAdmin>> futureLogs = logRepository.findAllByCartCheckoutOrderByDateDesc(cartCheckout);
//        List<LogAdmin> logs = futureLogs.get();
//
//        assertNotNull(logs);
//        assertEquals(2, logs.size());
//        assertEquals("Status changed to Dalam Pengiriman", logs.get(0).getLogString());
//        assertEquals(LocalDateTime.of(2024, 1, 2, 12, 0), logs.get(0).getDate());
//        assertEquals("Status changed to Menunggu Pengiriman Buku", logs.get(1).getLogString());
//        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), logs.get(1).getDate());
//    }
//}
