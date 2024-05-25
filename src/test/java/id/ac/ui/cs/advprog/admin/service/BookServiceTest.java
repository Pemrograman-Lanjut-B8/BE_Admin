//package id.ac.ui.cs.advprog.admin.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.concurrent.CompletableFuture;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import id.ac.ui.cs.advprog.admin.model.Book;
//import id.ac.ui.cs.advprog.admin.model.builders.BookBuilderImpl;
//import id.ac.ui.cs.advprog.admin.repository.BookRepository;
//
//@ExtendWith(MockitoExtension.class)
//public class BookServiceTest {
//    @InjectMocks
//    BookServiceImpl bookService;
//
//    @Mock
//    BookRepository bookRepository;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    void testAddBook() {
//        BookBuilderImpl bookBuilderImpl = new BookBuilderImpl();
//        bookBuilderImpl.setJudulBuku("Judul Buku 1");
//        bookBuilderImpl.setPenulis("Penulis 1");
//        bookBuilderImpl.setPenerbit("Penerbit 1");
//        bookBuilderImpl.setDeskripsi("Deskripsi 1");
//        bookBuilderImpl.setHarga(100000.0);
//        bookBuilderImpl.setStok(10);
//        bookBuilderImpl.setIsbn("ISBN 1");
//        bookBuilderImpl.setJumlahHalaman(100);
//        bookBuilderImpl.setFotoCover("Foto Cover 1");
//        bookBuilderImpl.setKategori("Kategori 1");
////        bookBuilderImpl.setRating(4.5);
//        bookBuilderImpl.setTanggalTerbit(LocalDate.parse("2020-01-01"));
//        Book book = bookBuilderImpl.getBook();
//
//        when(bookRepository.save(any(Book.class))).thenReturn(book);
//
//        CompletableFuture<Book> createdBookFuture = bookService.createBook(book);
//        Book createdBook = createdBookFuture.join();
//
//        assertEquals(book, createdBook);
//        verify(bookRepository, times(1)).save(book);
//    }
//
//    @Test
//    void testFindByIsbn() {
//        BookBuilderImpl bookBuilderImpl = new BookBuilderImpl();
//        bookBuilderImpl.setJudulBuku("Judul Buku 1");
//        bookBuilderImpl.setPenulis("Penulis 1");
//        bookBuilderImpl.setPenerbit("Penerbit 1");
//        bookBuilderImpl.setDeskripsi("Deskripsi 1");
//        bookBuilderImpl.setHarga(100000.0);
//        bookBuilderImpl.setStok(10);
//        bookBuilderImpl.setIsbn("ISBN 1");
//        bookBuilderImpl.setJumlahHalaman(100);
//        bookBuilderImpl.setFotoCover("Foto Cover 1");
//        bookBuilderImpl.setKategori("Kategori 1");
////        bookBuilderImpl.setRating(4.5);
//        bookBuilderImpl.setTanggalTerbit(LocalDate.parse("2020-01-01"));
//        Book book = bookBuilderImpl.getBook();
//
//        when(bookRepository.findByIsbn("ISBN 1")).thenReturn(book);
//
//        CompletableFuture<Book> foundBookFuture = bookService.findByIsbn("ISBN 1");
//        Book foundBook = foundBookFuture.join();
//
//        assertEquals(book, foundBook);
//        verify(bookRepository, times(1)).findByIsbn("ISBN 1");
//    }
//
//    @Test
//    void testDeleteBook() {
//        bookService.deleteByIsbn("ISBN 1");
//        verify(bookRepository, times(1)).delete(bookRepository.findByIsbn("ISBN 1"));
//    }
//
//    @Test
//    void testUpdateBook() {
//        BookBuilderImpl updatedBookBuilder = new BookBuilderImpl();
//        updatedBookBuilder.setJudulBuku("Judul Buku 2");
//        updatedBookBuilder.setPenulis("Penulis 2");
//        updatedBookBuilder.setPenerbit("Penerbit 2");
//        updatedBookBuilder.setDeskripsi("Deskripsi 2");
//        updatedBookBuilder.setHarga(200000.0);
//        updatedBookBuilder.setStok(20);
//        updatedBookBuilder.setIsbn("ISBN 2");
//        updatedBookBuilder.setJumlahHalaman(200);
//        updatedBookBuilder.setFotoCover("Foto Cover 2");
//        updatedBookBuilder.setKategori("Kategori 2");
////        updatedBookBuilder.setRating(4.0);
//        updatedBookBuilder.setTanggalTerbit(LocalDate.parse("2020-02-02"));
//        Book updatedBook = updatedBookBuilder.getBook();
//
//        bookService.update("ISBN 1", updatedBook);
//
//        verify(bookRepository, times(1)).findByIsbn("ISBN 1");
//        verify(bookRepository, times(1)).save(updatedBook);
//    }
//}
