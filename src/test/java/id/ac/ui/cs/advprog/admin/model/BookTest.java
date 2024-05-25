package id.ac.ui.cs.advprog.admin.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.admin.model.builders.BookBuilderImpl;

public class BookTest {
    private List<Book> books;
    private BookBuilderImpl bookBuilder;
    
    @BeforeEach
    void setup() {
        bookBuilder = new BookBuilderImpl();
        this.books = new ArrayList<>();
        bookBuilder.reset();
        bookBuilder.setJudulBuku("Judul Buku 1");
        bookBuilder.setPenulis("Penulis 1");
        bookBuilder.setPenerbit("Penerbit 1");
        bookBuilder.setDeskripsi("Deskripsi 1");
        bookBuilder.setHarga(100000.0);
        bookBuilder.setStok(10);
        bookBuilder.setIsbn("ISBN 1");
        bookBuilder.setJumlahHalaman(100);
        bookBuilder.setFotoCover("Foto Cover 1");
        bookBuilder.setKategori("Kategori 1");
//        bookBuilder.setRating(4.5);
        bookBuilder.setTanggalTerbit(LocalDate.parse("2020-01-01"));
        this.books.add(bookBuilder.getBook());

        bookBuilder.reset();
        bookBuilder.setJudulBuku("Judul Buku 2");
        bookBuilder.setPenulis("Penulis 2");
        bookBuilder.setPenerbit("Penerbit 2");
        bookBuilder.setDeskripsi("Deskripsi 2");
        bookBuilder.setHarga(200000.0);
        bookBuilder.setStok(20);
        bookBuilder.setIsbn("ISBN 2");
        bookBuilder.setJumlahHalaman(200);
        bookBuilder.setFotoCover("Foto Cover 2");
        bookBuilder.setKategori("Kategori 2");
//        bookBuilder.setRating(4.0);
        bookBuilder.setTanggalTerbit(LocalDate.parse("2020-02-02"));
        this.books.add(bookBuilder.getBook());
    }

    @Test
    void testAttributes() {
        assert books.get(0).getJudulBuku().equals("Judul Buku 1");
        assert books.get(0).getPenulis().equals("Penulis 1");
        assert books.get(0).getPenerbit().equals("Penerbit 1");
        assert books.get(0).getDeskripsi().equals("Deskripsi 1");
        assert books.get(0).getHarga() == 100000.0;
        assert books.get(0).getStok() == 10;
        assert books.get(0).getIsbn().equals("ISBN 1");
        assert books.get(0).getJumlahHalaman() == 100;
        assert books.get(0).getFotoCover().equals("Foto Cover 1");
        assert books.get(0).getKategori().equals("Kategori 1");
//        assert books.get(0).getRating() == 4.5;
        assert books.get(0).getTanggalTerbit().equals(LocalDate.parse("2020-01-01"));

        assert books.get(1).getJudulBuku().equals("Judul Buku 2");
        assert books.get(1).getPenulis().equals("Penulis 2");
        assert books.get(1).getPenerbit().equals("Penerbit 2");
        assert books.get(1).getDeskripsi().equals("Deskripsi 2");
        assert books.get(1).getHarga() == 200000.0;
        assert books.get(1).getStok() == 20;
        assert books.get(1).getIsbn().equals("ISBN 2");
        assert books.get(1).getJumlahHalaman() == 200;
        assert books.get(1).getFotoCover().equals("Foto Cover 2");
        assert books.get(1).getKategori().equals("Kategori 2");
//        assert books.get(1).getRating() == 4.0;
        assert books.get(1).getTanggalTerbit().equals(LocalDate.parse("2020-02-02"));
    }
}
