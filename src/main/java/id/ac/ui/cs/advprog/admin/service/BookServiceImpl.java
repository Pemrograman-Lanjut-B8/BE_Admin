package id.ac.ui.cs.advprog.admin.service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Transactional
    public void update(String isbn, 
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
    ) {
        bookRepository.update(
            judulBuku, 
            penulis, 
            penerbit, 
            deskripsi, 
            harga, 
            stok, 
            tanggalTerbit, 
            jumlahHalaman, 
            fotoCover, 
            kategori, 
            rating, 
            isbn);

        bookRepository.flush();

    }

    @Override
    public void deleteByIsbn(String isbn) {
        bookRepository.delete(bookRepository.findByIsbn(isbn));
    }
}
