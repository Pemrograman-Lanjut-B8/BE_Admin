package id.ac.ui.cs.advprog.admin.controller;

import id.ac.ui.cs.advprog.admin.dto.CartCheckoutAdminDTO;
import id.ac.ui.cs.advprog.admin.service.CartCheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pembelian-pelanggan")
public class PembelianPelangganController {

    @Autowired
    private CartCheckoutService cartCheckoutService;

    @GetMapping("/list-cartcheckout")
    public ResponseEntity<?> findAllCartCheckout () {
        List<CartCheckoutAdminDTO> cartCheckoutResult = cartCheckoutService.findAllCartCheckout();

        return ResponseEntity.ok(cartCheckoutResult);

    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable String id) {
        cartCheckoutService.updateStatus(Long.parseLong(id));

        return ResponseEntity.ok().build();
    }
}