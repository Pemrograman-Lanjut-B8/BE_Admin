package id.ac.ui.cs.advprog.admin.service;

import id.ac.ui.cs.advprog.admin.dto.CartCheckoutAdminDTO;
import id.ac.ui.cs.advprog.admin.model.CartCheckout;

import java.util.List;

public interface CartCheckoutService {
    List<CartCheckoutAdminDTO> findAllCartCheckout();
    CartCheckout findCartCheckoutById(Long id);
    List<CartCheckout> findCartCheckoutByEmail(String emailPembeli);
    List<CartCheckout> findCartCheckoutByBook(String book);
    void updateStatus(Long id);
}