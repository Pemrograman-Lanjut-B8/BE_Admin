package id.ac.ui.cs.advprog.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.model.builders.BookBuilderImpl;
import id.ac.ui.cs.advprog.admin.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddBook() {
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
        Book book = bookBuilderImpl.getBook();

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book createdBook = bookService.createBook(book);

        assertEquals(book, createdBook);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testFindByIsbn() {
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
        Book book = bookBuilderImpl.getBook();

        when(bookRepository.findByIsbn("ISBN 1")).thenReturn(book);

        Book foundBook = bookService.findByIsbn("ISBN 1");

        assertEquals(book, foundBook);
        verify(bookRepository, times(1)).findByIsbn("ISBN 1");
    }

    @Test
    void testDeleteBook() {
        bookService.deleteByIsbn("ISBN 1");
        verify(bookRepository, times(1)).delete(bookRepository.findByIsbn("ISBN 1"));
    }

    @Test
    void testUpdateBook() {
        bookService.update(
                "ISBN 1",
                "Judul Buku 2",
                "Penulis 2",
                "Penerbit 2",
                "Deskripsi 2",
                200000.0,
                20,
                LocalDate.parse("2020-02-02"),
                200,
                "Foto Cover 2",
                "Kategori 2",
                4.0
        );

        verify(bookRepository, times(1)).update(
                "Judul Buku 2",
                "Penulis 2",
                "Penerbit 2",
                "Deskripsi 2",
                200000.0,
                20,
                LocalDate.parse("2020-02-02"),
                200,
                "Foto Cover 2",
                "Kategori 2",
                4.0,
                "ISBN 1"
        );
    }

    @Test
    void testFindAll() {
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book());
        mockBooks.add(new Book());
        when(bookRepository.findAll()).thenReturn(mockBooks);

        List<Book> foundBooks = bookService.findAll();

        assertEquals(mockBooks.size(), foundBooks.size());
        assertEquals(mockBooks, foundBooks);
        verify(bookRepository, times(1)).findAll();
    }

}