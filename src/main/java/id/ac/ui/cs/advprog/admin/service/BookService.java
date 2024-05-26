package id.ac.ui.cs.advprog.admin.service;

import java.time.LocalDate;
import java.util.List;

import id.ac.ui.cs.advprog.admin.model.Book;

public interface BookService {
    public Book createBook(Book book);
    public List<Book> findAll();
    Book findByIsbn(String isbn);
    public void update(
        String isbn, 
        String judulBuku, 
        String penulis, 
        String penerbit, 
        String deskripsi, 
        double harga, 
        int stok, 
        LocalDate tanggalTerbit, 
        int jumlahHalaman, 
        String fotoCover, 
        String kategori,
        double rating
    );
    public void deleteByIsbn(String isbn);
}