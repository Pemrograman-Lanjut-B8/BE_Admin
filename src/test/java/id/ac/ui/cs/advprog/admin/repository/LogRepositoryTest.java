package id.ac.ui.cs.advprog.admin.repository;

import id.ac.ui.cs.advprog.admin.model.CartCheckout;
import id.ac.ui.cs.advprog.admin.model.LogAdmin;
import id.ac.ui.cs.advprog.admin.model.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LogRepositoryTest {

    @Autowired
    private LogRepository logRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private CartCheckout cartCheckout;

    @BeforeEach
    public void setUp() {
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        entityManager.persist(user);

        cartCheckout = new CartCheckout();
        cartCheckout.setUser(user);
        cartCheckout.setTotalPrice(100.0);
        cartCheckout.setStatus("PENDING");
        entityManager.persist(cartCheckout);

        LogAdmin log1 = new LogAdmin("Log message 1", cartCheckout);
        log1.setDate(LocalDateTime.now().minusDays(1));
        entityManager.persist(log1);

        LogAdmin log2 = new LogAdmin("Log message 2", cartCheckout);
        log2.setDate(LocalDateTime.now());
        entityManager.persist(log2);

        entityManager.flush();
    }

    @Test
    public void whenFindAllByCartCheckoutOrderByDateDesc_thenReturnLogs() throws ExecutionException, InterruptedException {
        CompletableFuture<List<LogAdmin>> logsFuture = logRepository.findAllByCartCheckoutOrderByDateDesc(cartCheckout);
        List<LogAdmin> logs = logsFuture.get();

        assertThat(logs).hasSize(2);
        assertThat(logs.get(0).getLogString()).isEqualTo("Log message 2");
        assertThat(logs.get(1).getLogString()).isEqualTo("Log message 1");
    }
}
