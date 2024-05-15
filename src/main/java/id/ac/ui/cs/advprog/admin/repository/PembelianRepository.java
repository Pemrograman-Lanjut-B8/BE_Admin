package id.ac.ui.cs.advprog.admin.repository;

import id.ac.ui.cs.advprog.admin.model.Pembelian;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PembelianRepository extends JpaRepository<Pembelian, Long> {

    Optional<Pembelian> findPembelianPelangganById(Long id);

    List<Pembelian> findPembelianPelangganByEmail(String email);

    List<Pembelian> findPembelianPelangganByBuku(String book);

    void updatePembelianStatus(Long id, String newStatus);
}