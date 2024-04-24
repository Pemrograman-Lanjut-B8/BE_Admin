package id.ac.ui.cs.advprog.admin.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Book {
    private String judulBuku;
    private String penulis;
    private String penerbit;
    private String deskripsi;
    private double harga;
    private int stok;
    private LocalDate tanggalTerbit;
    private String isbn;
    private int jumlahHalaman;
    private String fotoCover;
    private String kategori;
    private double rating;
}
