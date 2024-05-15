package id.ac.ui.cs.advprog.admin.service;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.model.CartCheckout;
import id.ac.ui.cs.advprog.admin.model.UserEntity;
import id.ac.ui.cs.advprog.admin.repository.CartCheckoutRepository;

import id.ac.ui.cs.advprog.admin.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CartCheckoutServiceImpl implements CartCheckoutService {

    @Autowired
    private CartCheckoutRepository cartCheckoutRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    public List<CartCheckout> findAllCartCheckout() {
            return cartCheckoutRepository.findAll();
    }

    public CartCheckout findCartCheckoutById(Long id) {
        return cartCheckoutRepository.findCartCheckoutById(id);
    }

    public List<CartCheckout> findCartCheckoutByEmail(String emailPembeli) {
        UserEntity user = userEntityRepository.findUserEntityByEmail(emailPembeli);

        return cartCheckoutRepository.findCartCheckoutByUser(user);
    }

    public List<CartCheckout> findCartCheckoutByBook(String book) {

        List<CartCheckout> cartCheckouts = cartCheckoutRepository.findAll();

        return cartCheckouts.stream().filter(cartCheckout ->
            cartCheckout.getItems().stream().anyMatch(cartItems ->
                 cartItems.getBook().getJudulBuku().contains(book)
            )
        ).collect(Collectors.toList());

    }


    public void updateStatus(Long id) {
        CartCheckout cartCheckout  = cartCheckoutRepository.findCartCheckoutById(id);

        if (cartCheckout == null) {
            throw new RuntimeException("Pembelian not found");
        }

        String status = cartCheckout.getStatus();
        if (status.equals("Menunggu Pengiriman Buku")) {
            cartCheckout.setStatus("Dalam Pengiriman");

        }

        else if (status.equals("Dalam Pengiriman")) {
            cartCheckout.setStatus("Pengiriman Selesai");
        }

        cartCheckoutRepository.save(cartCheckout);
    }
}