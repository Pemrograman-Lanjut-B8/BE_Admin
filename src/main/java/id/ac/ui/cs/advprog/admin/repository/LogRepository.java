package id.ac.ui.cs.advprog.admin.repository;

import id.ac.ui.cs.advprog.admin.model.CartCheckout;
import id.ac.ui.cs.advprog.admin.model.LogAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface LogRepository extends JpaRepository<LogAdmin, Integer> {

    CompletableFuture<List<LogAdmin>> findAllByCartCheckoutOrderByDateDesc(CartCheckout cartCheckout);
}
