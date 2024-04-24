package id.ac.ui.cs.advprog.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.model.builders.BookBuilderImpl;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {
    @InjectMocks
    BookRepository bookRepository;

    @Mock
    BookBuilderImpl bookBuilder;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        BookBuilderImpl bookBuilderImpl = new BookBuilderImpl();
        bookBuilderImpl.setJudulBuku("Judul Buku 1");
        bookBuilderImpl.setPenulis("Penulis 1");
        bookBuilderImpl.setPenerbit("Penerbit 1");
        bookBuilderImpl.setDeskripsi("Deskripsi 1");
        bookBuilderImpl.setHarga(100000.0);
        bookBuilderImpl.setStok(10);
        bookBuilderImpl.setIsbn("ISBN 1");
        bookBuilderImpl.setJumlahHalaman(100);
        bookBuilderImpl.setFotoCover("Foto Cover 1");
        bookBuilderImpl.setKategori("Kategori 1");
        bookBuilderImpl.setRating(4.5);
        bookBuilderImpl.setTanggalTerbit(LocalDate.parse("2020-01-01"));
        
        when(bookBuilder.getBook()).thenReturn(bookBuilderImpl.getBook());
    
        bookRepository.createBook(bookBuilder.getBook());
    
        Iterator<Book> books = bookRepository.findAll();
        assertTrue(books.hasNext());
        Book savedBook = books.next();
        assertTrue(savedBook.getJudulBuku().equals("Judul Buku 1"));
        assertTrue(savedBook.getPenulis().equals("Penulis 1"));
        assertTrue(savedBook.getPenerbit().equals("Penerbit 1"));
        assertTrue(savedBook.getDeskripsi().equals("Deskripsi 1"));
        assertTrue(savedBook.getHarga() == 100000.0);
        assertTrue(savedBook.getStok() == 10);
        assertTrue(savedBook.getIsbn().equals("ISBN 1"));
        assertTrue(savedBook.getJumlahHalaman() == 100);
        assertTrue(savedBook.getFotoCover().equals("Foto Cover 1"));
        assertTrue(savedBook.getKategori().equals("Kategori 1"));
        assertTrue(savedBook.getRating() == 4.5);
        assertTrue(savedBook.getTanggalTerbit().equals(LocalDate.parse("2020-01-01")));
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Book> books = bookRepository.findAll();
        assertFalse(books.hasNext());
    }
    
    @Test
    void deleteBook() {
        BookBuilderImpl bookBuilderImpl = new BookBuilderImpl();
        bookBuilderImpl.setJudulBuku("Judul Buku 1");
        bookBuilderImpl.setPenulis("Penulis 1");
        bookBuilderImpl.setPenerbit("Penerbit 1");
        bookBuilderImpl.setDeskripsi("Deskripsi 1");
        bookBuilderImpl.setHarga(100000.0);
        bookBuilderImpl.setStok(10);
        bookBuilderImpl.setIsbn("ISBN 1");
        bookBuilderImpl.setJumlahHalaman(100);
        bookBuilderImpl.setFotoCover("Foto Cover 1");
        bookBuilderImpl.setKategori("Kategori 1");
        bookBuilderImpl.setRating(4.5);
        bookBuilderImpl.setTanggalTerbit(LocalDate.parse("2020-01-01"));
    
        bookRepository.createBook(bookBuilderImpl.getBook());
    
        bookRepository.delete("ISBN 1");
    
        Iterator<Book> books = bookRepository.findAll();
        assertFalse(books.hasNext());
    }
    
    @Test
    void updateBook() {
        BookBuilderImpl bookBuilderImpl1 = new BookBuilderImpl();
        bookBuilderImpl1.setJudulBuku("Judul Buku 1");
        bookBuilderImpl1.setPenulis("Penulis 1");
        bookBuilderImpl1.setPenerbit("Penerbit 1");
        bookBuilderImpl1.setDeskripsi("Deskripsi 1");
        bookBuilderImpl1.setHarga(100000.0);
        bookBuilderImpl1.setStok(10);
        bookBuilderImpl1.setIsbn("ISBN 1");
        bookBuilderImpl1.setJumlahHalaman(100);
        bookBuilderImpl1.setFotoCover("Foto Cover 1");
        bookBuilderImpl1.setKategori("Kategori 1");
        bookBuilderImpl1.setRating(4.5);
        bookBuilderImpl1.setTanggalTerbit(LocalDate.parse("2020-01-01"));
        
        when(bookBuilder.getBook()).thenReturn(bookBuilderImpl1.getBook());

        bookRepository.createBook(bookBuilder.getBook());
    
        BookBuilderImpl bookBuilderImpl2 = new BookBuilderImpl();
        bookBuilderImpl2.setJudulBuku("Judul Buku 2");
        bookBuilderImpl2.setPenulis("Penulis 2");
        bookBuilderImpl2.setPenerbit("Penerbit 2");
        bookBuilderImpl2.setDeskripsi("Deskripsi 2");
        bookBuilderImpl2.setHarga(200000.0);
        bookBuilderImpl2.setStok(20);
        bookBuilderImpl2.setIsbn("ISBN 2");
        bookBuilderImpl2.setJumlahHalaman(200);
        bookBuilderImpl2.setFotoCover("Foto Cover 2");
        bookBuilderImpl2.setKategori("Kategori 2");
        bookBuilderImpl2.setRating(4.0);
        bookBuilderImpl2.setTanggalTerbit(LocalDate.parse("2020-02-02"));

        when(bookBuilder.getBook()).thenReturn(bookBuilderImpl2.getBook());
    
        bookRepository.update("ISBN 1", bookBuilder.getBook());
        
        Iterator<Book> books = bookRepository.findAll();
        assertTrue(books.hasNext());
        Book updatedBook = books.next();
        assertEquals("Judul Buku 2", updatedBook.getJudulBuku());
        assertEquals("Penulis 2", updatedBook.getPenulis());
        assertEquals("Penerbit 2", updatedBook.getPenerbit());
        assertEquals("Deskripsi 2", updatedBook.getDeskripsi());
        assertEquals(200000.0, updatedBook.getHarga());
        assertEquals(20, updatedBook.getStok());
        assertEquals("ISBN 2", updatedBook.getIsbn());
        assertEquals(200, updatedBook.getJumlahHalaman());
        assertEquals("Foto Cover 2", updatedBook.getFotoCover());
        assertEquals("Kategori 2", updatedBook.getKategori());
        assertEquals(4.0, updatedBook.getRating());
        assertEquals(LocalDate.parse("2020-02-02"), updatedBook.getTanggalTerbit());
    }
}    