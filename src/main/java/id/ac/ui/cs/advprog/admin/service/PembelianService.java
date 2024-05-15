package id.ac.ui.cs.advprog.admin.service;

import id.ac.ui.cs.advprog.admin.model.Pembelian;

import java.util.List;
import java.util.Optional;

public interface PembelianService {
    List<Pembelian> findAllPembelianPelanggan();
    Optional<Pembelian> findPembelianPelangganById(Long id);
    List<Pembelian> findPembelianPelangganByEmail(String email);
    List<Pembelian> findPembelianPelangganByBuku(String buku);
    void updateStatus(Long id, String status);
}