package id.ac.ui.cs.advprog.admin.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    @Async
    public CompletableFuture<Book> createBook(Book book) {
        return CompletableFuture.completedFuture(bookRepository.save(book));
    }

    @Override
    @Async
    public CompletableFuture<List<Book>> findAll() {
        return CompletableFuture.completedFuture(bookRepository.findAll());
    }

    @Override
    @Async
    public CompletableFuture<Book> findByIsbn(String isbn) {
        return CompletableFuture.completedFuture(bookRepository.findByIsbn(isbn));
    }

    @Override
    @Async
    public CompletableFuture<Book> update(String isbn, Book newBook) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book != null) {
            book.setJudulBuku(newBook.getJudulBuku());
            book.setPenulis(newBook.getPenulis());
            book.setPenerbit(newBook.getPenerbit());
            book.setDeskripsi(newBook.getDeskripsi());
            book.setHarga(newBook.getHarga());
            book.setStok(newBook.getStok());
            book.setTanggalTerbit(newBook.getTanggalTerbit());
            book.setJumlahHalaman(newBook.getJumlahHalaman());
            book.setFotoCover(newBook.getFotoCover());
            book.setKategori(newBook.getKategori());
//            book.setRating(newBook.getRating());
            return CompletableFuture.completedFuture(bookRepository.save(book));
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async
    public CompletableFuture<Void> deleteByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book != null) {
            bookRepository.delete(book);
        }
        return CompletableFuture.completedFuture(null);
    }
}
