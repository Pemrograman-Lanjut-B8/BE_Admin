package id.ac.ui.cs.advprog.admin.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookDto {
    private String judulBuku;
    private String penulis;
    private String penerbit;
    private LocalDate tanggalTerbit;
    private String deskripsi;
    private double harga;
    private int stok;
    private String isbn;
    private int jumlahHalaman;
    private String fotoCover;
    private String kategori;
    private double rating;

    public BookDto(
        String judulBuku, 
        String penulis, 
        String penerbit, 
        LocalDate tanggalTerbit, 
        String deskripsi, 
        double harga, 
        int stok, 
        String isbn, 
        int jumlahHalaman, 
        String fotoCover, 
        String kategori,
        double rating)
    {
        this.judulBuku = judulBuku;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.tanggalTerbit = tanggalTerbit;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.stok = stok;
        this.isbn = isbn;
        this.jumlahHalaman = jumlahHalaman;
        this.fotoCover = fotoCover;
        this.kategori = kategori;
        this.rating = rating;
    }
}