package id.ac.ui.cs.advprog.admin.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "book")
@NoArgsConstructor
public class Book {
    @Column(name = "judul_buku")
    private String judulBuku;

    @Column(name = "penulis")
    private String penulis;

    @Column(name = "penerbit")
    private String penerbit;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "harga")
    private double harga;

    @Column(name = "stok")
    private int stok;

    @Column(name = "tanggal_terbit")
    private LocalDate tanggalTerbit;

    @Column(name = "isbn")
    @Id
    private String isbn;

    @Column(name = "jumlah_halaman")
    private int jumlahHalaman;

    @Column(name = "foto_cover")
    private String fotoCover;

    @Column(name = "kategori")
    private String kategori;

    @Column(name = "rating")
    private double rating;

}
