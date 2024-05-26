package id.ac.ui.cs.advprog.admin.repository;

import id.ac.ui.cs.advprog.admin.model.CartCheckout;

import java.util.*;

import id.ac.ui.cs.advprog.admin.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartCheckoutRepository extends JpaRepository<CartCheckout, Long> {

    CartCheckout findCartCheckoutById(Long id);

    List<CartCheckout> findCartCheckoutByUser(UserEntity user);

}