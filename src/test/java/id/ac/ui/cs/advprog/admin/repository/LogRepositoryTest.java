package id.ac.ui.cs.advprog.admin.repository;

import id.ac.ui.cs.advprog.admin.model.CartCheckout;
import id.ac.ui.cs.advprog.admin.model.LogAdmin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LogRepositoryTest {

    @Autowired
    private LogRepository logRepository;

    private CartCheckout cartCheckout;

    @BeforeEach
    void setUp() {
        cartCheckout = new CartCheckout();
        // Set up properties for cartCheckout if needed
    }

    @AfterEach
    void tearDown() {
        logRepository.deleteAll();
    }

    @Test
    void testFindAllByCartCheckoutOrderByDateDesc() throws InterruptedException, ExecutionException, TimeoutException {
        // Create test logs
        LogAdmin log1 = new LogAdmin("Test log 1", cartCheckout);
        LogAdmin log2 = new LogAdmin("Test log 2", cartCheckout);

        logRepository.save(log1);
        logRepository.save(log2);

        // Fetch logs for the cartCheckout
        List<LogAdmin> logs = logRepository.findAllByCartCheckoutOrderByDateDesc(cartCheckout)
                .get(5, TimeUnit.SECONDS);

        // Assertions
        assertNotNull(logs);
        assertFalse(logs.isEmpty());
        assertEquals(2, logs.size());

        // Assert the order is descending by date
        assertTrue(logs.get(0).getDate().isAfter(logs.get(1).getDate()));
    }

    @Test
    void testFindAllByCartCheckoutOrderByDateDesc_EmptyResult() throws InterruptedException, ExecutionException, TimeoutException {
        // Fetch logs for a new cartCheckout, expecting empty result
        List<LogAdmin> logs = logRepository.findAllByCartCheckoutOrderByDateDesc(cartCheckout)
                .get(5, TimeUnit.SECONDS);

        // Assertions
        assertNotNull(logs);
        assertTrue(logs.isEmpty());
    }

    @Test
    void testFindAllByCartCheckoutOrderByDateDesc_CustomOrder() throws InterruptedException, ExecutionException, TimeoutException {
        // Create test logs with different dates
        LogAdmin log1 = new LogAdmin("Test log 1", cartCheckout);
        LogAdmin log2 = new LogAdmin("Test log 2", cartCheckout);
        LogAdmin log3 = new LogAdmin("Test log 3", cartCheckout);

        // Set custom dates
        log1.setDate(LocalDateTime.now().minusDays(3));
        log2.setDate(LocalDateTime.now().minusDays(1));
        log3.setDate(LocalDateTime.now().minusDays(2));

        logRepository.save(log1);
        logRepository.save(log2);
        logRepository.save(log3);

        // Fetch logs for the cartCheckout
        List<LogAdmin> logs = logRepository.findAllByCartCheckoutOrderByDateDesc(cartCheckout)
                .get(5, TimeUnit.SECONDS);

        // Assertions
        assertNotNull(logs);
        assertFalse(logs.isEmpty());
        assertEquals(3, logs.size());

        // Collect dates
        List<LocalDateTime> dates = logs.stream().map(LogAdmin::getDate).collect(Collectors.toList());

        // Assert the order is descending by date
        for (int i = 0; i < dates.size() - 1; i++) {
            assertTrue(dates.get(i).isAfter(dates.get(i + 1)));
        }
    }

    // Add more test cases for edge cases if needed
}
