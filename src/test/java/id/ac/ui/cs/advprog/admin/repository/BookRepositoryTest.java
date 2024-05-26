package id.ac.ui.cs.advprog.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.model.builders.BookBuilderImpl;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    BookBuilderImpl bookBuilder;

    private Book testBook;

    @BeforeEach
    void setUp() {
        bookBuilder = new BookBuilderImpl();
        bookBuilder.reset();
        bookBuilder.setJudulBuku("Test Title");
        bookBuilder.setPenulis("Test Author");
        bookBuilder.setPenerbit("Test Publisher");
        bookBuilder.setDeskripsi("Test Description");
        bookBuilder.setHarga(20.0);
        bookBuilder.setStok(10);
        bookBuilder.setTanggalTerbit(LocalDate.of(2022, 1, 1));
        bookBuilder.setJumlahHalaman(200);
        bookBuilder.setFotoCover("test.jpg");
        bookBuilder.setKategori("Test Category");
        bookBuilder.setIsbn("1234567890");
        testBook = bookBuilder.getBook();
    }

    @Test
    void testCreateAndFind() {
        when(bookRepository.save(testBook)).thenReturn(testBook);
        Book savedBook = bookRepository.save(testBook);
        assertEquals(testBook, savedBook);
        when(bookRepository.findByIsbn(testBook.getIsbn())).thenReturn(testBook);
        Book foundBook = bookRepository.findByIsbn(testBook.getIsbn());
        assertEquals(testBook, foundBook);
    }

    @Test
    void testFindAllIfEmpty() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<>());
        List<Book> books = bookRepository.findAll();
        assertTrue(books.isEmpty());
    }

    @Test
    void deleteBook() {
        bookRepository.delete(testBook);
        verify(bookRepository).delete(testBook);
    }

    @Test
    void updateBook() {
        when(bookRepository.save(testBook)).thenReturn(testBook);
        Book updatedBook = bookRepository.save(testBook);
        assertEquals(testBook, updatedBook);
        bookRepository.update("Updated Title", "Updated Author", "Updated Publisher",
                "Updated Description", 30.0, 20, LocalDate.of(2023, 1, 1), 250,
                "updated.jpg", "Updated Category", 4.0, testBook.getIsbn());
        verify(bookRepository).update("Updated Title", "Updated Author", "Updated Publisher",
                "Updated Description", 30.0, 20, LocalDate.of(2023, 1, 1), 250,
                "updated.jpg", "Updated Category", 4.0, testBook.getIsbn());
    }
}