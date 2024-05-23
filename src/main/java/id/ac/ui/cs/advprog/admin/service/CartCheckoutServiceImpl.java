package id.ac.ui.cs.advprog.admin.service;

import id.ac.ui.cs.advprog.admin.dto.CartCheckoutAdminDTO;
import id.ac.ui.cs.advprog.admin.dto.CartCheckoutDTO;
import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.model.CartCheckout;
import id.ac.ui.cs.advprog.admin.model.LogAdmin;
import id.ac.ui.cs.advprog.admin.model.UserEntity;
import id.ac.ui.cs.advprog.admin.repository.CartCheckoutRepository;

import id.ac.ui.cs.advprog.admin.repository.LogRepository;
import id.ac.ui.cs.advprog.admin.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
public class CartCheckoutServiceImpl implements CartCheckoutService {

    @Autowired
    private CartCheckoutRepository cartCheckoutRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<CartCheckoutAdminDTO> findAllCartCheckout() {
        String url = "http://localhost:8090/cart/list";

        ResponseEntity<CartCheckoutDTO[]> cartCheckoutResult = restTemplate.getForEntity(url, CartCheckoutDTO[].class);

        if (cartCheckoutResult.getStatusCode().is2xxSuccessful()) {
            return List.of(cartCheckoutResult.getBody()).stream().map(
                    cartCheckoutDTO -> {
                        CartCheckoutAdminDTO cartCheckout = new CartCheckoutAdminDTO();
                        Optional<UserEntity> userOptional = userEntityRepository.findById(UUID.fromString(cartCheckoutDTO.getUserId()));

                        if (userOptional.isEmpty()) {
                            return null;
                        }

                        UserEntity user = userOptional.get();
                        cartCheckout.setId(cartCheckoutDTO.getId());
                        cartCheckout.setItems(cartCheckoutDTO.getItems());
                        cartCheckout.setNamaUser(user.getFullName());
                        cartCheckout.setEmailUser(user.getEmail());
                        cartCheckout.setPhoneNumberUser(user.getPhoneNumber());
                        cartCheckout.setTotalPrice(cartCheckoutDTO.getTotalPrice());
                        cartCheckout.setStatus(cartCheckoutDTO.getStatus());

                        return cartCheckout;
                    }

            ).collect(Collectors.toList());
        }

        return new ArrayList<>();

    }

//    public List<CartCheckout> findAllCartCheckout() {
//            return cartCheckoutRepository.findAll();
//    }

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
            status = "Dalam Pengiriman";

        }

        else if (status.equals("Dalam Pengiriman")) {
           status = "Pengiriman Selesai";
        }

        cartCheckout.setStatus(status);

        String finalStatus = status;

        CompletableFuture.runAsync(() -> logUpdateStatus(cartCheckout, finalStatus));
        cartCheckoutRepository.save(cartCheckout);
    }


    public void logUpdateStatus(CartCheckout cartCheckout, String status) {

        long id = cartCheckout.getId();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String logString = "Status Checkout dengan id: "+ id + " berhasil diubah menjadi " + status + " pada " + date;

        LogAdmin log = new LogAdmin(logString, cartCheckout);

        logRepository.save(log);
    }

    public CompletableFuture<List<LogAdmin>> getLog (CartCheckout cartCheckout) {
        return logRepository.findAllByCartCheckoutOrderByDateDesc(cartCheckout);
    }

}