package id.ac.ui.cs.advprog.admin.service;

import id.ac.ui.cs.advprog.admin.model.Pembelian;
import id.ac.ui.cs.advprog.admin.repository.PembelianRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;

@Service
public class PembelianServiceImpl implements PembelianService {
    private PembelianRepository pembelianRepository;

    public PembelianServiceImpl(PembelianRepository pembelianRepository) {
        this.pembelianRepository = pembelianRepository;
    }

    public List<Pembelian> findAllPembelianPelanggan() {
            return pembelianRepository.findAll();
    }

    public Optional<Pembelian> findPembelianPelangganById(Long id) {
        return pembelianRepository.findPembelianPelangganById(id);
    }

    public List<Pembelian> findPembelianPelangganByEmail(String email) {
        return pembelianRepository.findPembelianPelangganByEmail(email);
    }

    public List<Pembelian> findPembelianPelangganByBuku(String book) {
        return pembelianRepository.findPembelianPelangganByBuku(book);
    }

    public void updateStatus(Long id, String status) {
        Optional<Pembelian> pembelianOptional = findPembelianPelangganById(id);
        if (pembelianOptional.isPresent()) {
            Pembelian pembelian = pembelianOptional.get();
            String statusLama = pembelian.getStatus();
            if (statusLama.equals("Menunggu Pengiriman Buku") && status.equals("Dalam Pengiriman")) {
                pembelian.setStatus(status);
                pembelianRepository.save(pembelian);
            } else if (statusLama.equals("Dalam Pengiriman") && status.equals("Pengiriman Selesai")) {
                pembelian.setStatus(status);
                pembelianRepository.save(pembelian);
            } else {
                throw new RuntimeException("Invalid status transition");
            }
        } else {
            throw new RuntimeException("Pembelian not found");
        }
    }
}