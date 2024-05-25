package id.ac.ui.cs.advprog.admin.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.model.builders.BookBuilderImpl;
import id.ac.ui.cs.advprog.admin.service.BookService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookBuilderImpl bookBuilder;

    @GetMapping("/")
    @ResponseBody
    public String adminFrontPage() {
        return "<h1>Welcome to Admin Page</h1>";
    }

    @PostMapping("/book")
    public CompletableFuture<ResponseEntity<?>> addBook(@RequestBody Map<String, String> bookData) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                validateBookData(bookData);
                prepareBookBuilder(bookData);
                Book addedBook = bookService.createBook(bookBuilder.getBook()).join();
                return ResponseEntity.ok(addedBook);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("Failed to create book, invalid data format");
            }
        });
    }

    @PutMapping("/book/{isbn}")
    public CompletableFuture<ResponseEntity<?>> updateBook(@PathVariable String isbn, @RequestBody Map<String, String> bookData) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Book existingBook = bookService.findByIsbn(isbn).join();
                if (existingBook == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                         .body("Book with ISBN " + isbn + " not found");
                }
                validateBookData(bookData);
                prepareBookBuilder(bookData);
                Book updatedBook = bookService.update(isbn, bookBuilder.getBook()).join();
                return ResponseEntity.ok(updatedBook);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("Failed to update book, invalid data format");
            }
        });
    }

    @DeleteMapping("/book/{isbn}")
    public CompletableFuture<ResponseEntity<?>> deleteBook(@PathVariable String isbn) {
        return CompletableFuture.supplyAsync(() -> {
            Book existingBook = bookService.findByIsbn(isbn).join();
            if (existingBook == null) {
                return ResponseEntity.notFound().build();
            }
            bookService.deleteByIsbn(isbn).join();
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/book/{isbn}")
    public CompletableFuture<ResponseEntity<?>> getBook(@PathVariable String isbn) {
        return CompletableFuture.supplyAsync(() -> {
            Book existingBook = bookService.findByIsbn(isbn).join();
            if (existingBook == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(existingBook);
        });
    }

    @GetMapping("/books")
    public CompletableFuture<ResponseEntity<?>> getAllBooks() {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(bookService.findAll().join()));
    }

    private void validateBookData(Map<String, String> bookData) throws IllegalArgumentException {
        if (
            !bookData.containsKey("judulBuku") ||
            !bookData.containsKey("penulis") ||
            !bookData.containsKey("penerbit") ||
            !bookData.containsKey("deskripsi") ||
            !bookData.containsKey("harga") ||
            !bookData.containsKey("stok") ||
            !bookData.containsKey("isbn") ||
            !bookData.containsKey("jumlahHalaman") ||
            !bookData.containsKey("fotoCover") ||
            !bookData.containsKey("kategori") ||
            !bookData.containsKey("rating") ||
            !bookData.containsKey("tanggalTerbit")
        ) {
            throw new IllegalArgumentException("Incomplete book data");
        }
    }

    private void prepareBookBuilder(Map<String, String> bookData) {
        bookBuilder.reset();
        bookBuilder.setJudulBuku(bookData.get("judulBuku"));
        bookBuilder.setPenulis(bookData.get("penulis"));
        bookBuilder.setPenerbit(bookData.get("penerbit"));
        bookBuilder.setDeskripsi(bookData.get("deskripsi"));
        bookBuilder.setHarga(Double.parseDouble(bookData.get("harga")));
        bookBuilder.setStok(Integer.parseInt(bookData.get("stok")));
        bookBuilder.setIsbn(bookData.get("isbn"));
        bookBuilder.setJumlahHalaman(Integer.parseInt(bookData.get("jumlahHalaman")));
        bookBuilder.setFotoCover(bookData.get("fotoCover"));
        bookBuilder.setKategori(bookData.get("kategori"));
//        bookBuilder.setRating(Double.parseDouble(bookData.get("rating")));
        bookBuilder.setTanggalTerbit(LocalDate.parse(bookData.get("tanggalTerbit")));
    }
}
