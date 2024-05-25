package id.ac.ui.cs.advprog.admin.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.admin.model.Book;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, String>{
    @SuppressWarnings("null")
    List<Book> findAll();
    Book findByIsbn(String isbn);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Book b set b.judulBuku = ?1, b.penulis = ?2, b.penerbit = ?3, b.deskripsi = ?4, b.harga = ?5, b.stok = ?6, b.tanggalTerbit = ?7, b.jumlahHalaman = ?8, b.fotoCover = ?9, b.kategori = ?10, b.rating = ?11 where b.isbn = ?12")
    void update(String judulBuku, String penulis, String penerbit, String deskripsi, double harga, int stok, LocalDate tanggalTerbit, int jumlahHalaman, String fotoCover, String kategori, double rating, String isbn);
}
