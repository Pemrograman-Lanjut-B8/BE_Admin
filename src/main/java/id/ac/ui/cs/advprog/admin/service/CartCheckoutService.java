package id.ac.ui.cs.advprog.admin.service;

import id.ac.ui.cs.advprog.admin.model.CartCheckout;

import java.util.List;

public interface CartCheckoutService {
    List<CartCheckout> findAllCartCheckout();
    CartCheckout findCartCheckoutById(Long id);
    List<CartCheckout> findCartCheckoutByEmail(String emailPembeli);
    List<CartCheckout> findCartCheckoutByBook(String book);
    void updateStatus(Long id);
}