package id.ac.ui.cs.advprog.admin.model.builders;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.model.interfaces.BookBuilder;

@Component
public class BookBuilderImpl implements BookBuilder {
    private Book book;

    public BookBuilderImpl() {
        this.reset();
    }
    
    @Override
    public void reset() {
        book = new Book();
    }

    @Override
    public void setJudulBuku(String judulBuku) {
        book.setJudulBuku(judulBuku);
    }

    @Override
    public void setPenulis(String penulis) {
        book.setPenulis(penulis);
    }

    @Override
    public void setPenerbit(String penerbit) {
        book.setPenerbit(penerbit);
    }

    @Override
    public void setDeskripsi(String deskripsi) {
        book.setDeskripsi(deskripsi);
    }

    @Override
    public void setHarga(double harga) {
        book.setHarga(harga);
    }

    @Override
    public void setStok(int stok) {
        book.setStok(stok);
    }

    @Override
    public void setTanggalTerbit(LocalDate tanggalTerbit) {
        book.setTanggalTerbit(tanggalTerbit);
    }

    @Override
    public void setIsbn(String isbn) {
        book.setIsbn(isbn);
    }

    @Override
    public void setJumlahHalaman(int jumlahHalaman) {
        book.setJumlahHalaman(jumlahHalaman);
    }

    @Override
    public void setFotoCover(String fotoCover) {
        book.setFotoCover(fotoCover);
    }

    @Override
    public void setKategori(String kategori) {
        book.setKategori(kategori);
    }

//    @Override
//    public void setRating(double rating) {
//        book.setRating(rating);
//    }
    
    public Book getBook() {
        Book result = this.book;
        this.reset();
        return result;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
