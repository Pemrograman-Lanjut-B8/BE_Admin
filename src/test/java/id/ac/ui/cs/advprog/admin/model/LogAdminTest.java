package id.ac.ui.cs.advprog.admin.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LogAdminTest {

    private LogAdmin logAdmin;
    private CartCheckout cartCheckout;

    @BeforeEach
    void setUp() {
        cartCheckout = new CartCheckout();
        cartCheckout.setId(1L); // Set ID
        // Set more properties if needed

        logAdmin = new LogAdmin("Test log", cartCheckout);
    }

    @Test
    void testConstructorAndGetters() {
        assertNotNull(logAdmin);

        assertEquals("Test log", logAdmin.getLogString());
        assertEquals(cartCheckout, logAdmin.getCartCheckout());
        assertNotNull(logAdmin.getDate());
    }

    @Test
    void testDateInstantiatedCorrectly() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime logDate = logAdmin.getDate();

        assertEquals(now.getYear(), logDate.getYear());
        assertEquals(now.getMonth(), logDate.getMonth());
        assertEquals(now.getDayOfMonth(), logDate.getDayOfMonth());
        assertEquals(now.getHour(), logDate.getHour());
        assertEquals(now.getMinute(), logDate.getMinute());
        // Add more assertions for seconds and milliseconds if needed
    }

    // Add more test cases for edge cases if needed
}
