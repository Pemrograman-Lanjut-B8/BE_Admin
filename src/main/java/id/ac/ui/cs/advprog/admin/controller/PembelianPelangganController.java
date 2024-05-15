package id.ac.ui.cs.advprog.admin.controller;

import id.ac.ui.cs.advprog.admin.model.Pembelian;
import id.ac.ui.cs.advprog.admin.service.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PembelianPelangganController {
    private PembelianService pembelianService;

    @Autowired
    public PembelianPelangganController(PembelianService pembelianService) {
        this.pembelianService = pembelianService;
    }

    @GetMapping("/pembelian-pelanggan")
    public List<Pembelian> getAllPembelianPelanggan() {
        return pembelianService.findAllPembelianPelanggan();
    }

    @GetMapping("/pembelian-pelanggan/{id}")
    public Optional<Pembelian> getPembelianPelangganById(@PathVariable Long id) {
        return pembelianService.findPembelianPelangganById(id);
    }

    @GetMapping("/pembelian-pelanggan/email/{email}")
    public List<Pembelian> getPembelianPelangganByEmail(@PathVariable String email) {
        return pembelianService.findPembelianPelangganByEmail(email);
    }

    @GetMapping("/pembelian-pelanggan/buku/{buku}")
    public List<Pembelian> getPembelianPelangganByBuku(@PathVariable String buku) {
        return pembelianService.findPembelianPelangganByBuku(buku);
    }

    @PostMapping("/pembelian-pelanggan/{id}/update-status")
    public void updateStatus(@PathVariable Long id, @RequestBody String status) {
        pembelianService.updateStatus(id, status);
    }
}