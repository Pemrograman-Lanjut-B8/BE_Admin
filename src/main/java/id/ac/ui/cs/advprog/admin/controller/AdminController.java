package id.ac.ui.cs.advprog.admin.controller;

import java.time.LocalDate;
import java.util.Map;

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
    public ResponseEntity<?> addBook(
        @RequestBody Map<String, String> bookData
    ) {
        // Check if the request body contains the required fields and if the fields are valid
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Data buku tidak lengkap");
        }

        try {
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
            bookBuilder.setRating(Double.parseDouble(bookData.get("rating")));
            bookBuilder.setTanggalTerbit(LocalDate.parse(bookData.get("tanggalTerbit")));
            Book addedBook = bookService.createBook(bookBuilder.getBook());
            return ResponseEntity.ok(addedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Gagal membuat buku, format data tidak valid");
        }
    }

    @PutMapping("/book/{isbn}")
    public ResponseEntity<?> updateBook(
        @PathVariable String isbn,
        @RequestBody Map<String, String> bookData
    ) {
        // Check if the book exists
        if (bookService.findByIsbn(isbn) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Buku dengan ISBN " + isbn + " tidak ditemukan");
        }
        
        // Check if the request body contains the required fields and if the fields are valid
        if (
            !bookData.containsKey("judulBuku") ||
            !bookData.containsKey("penulis") ||
            !bookData.containsKey("penerbit") ||
            !bookData.containsKey("deskripsi") ||
            !bookData.containsKey("harga") ||
            !bookData.containsKey("stok") ||
            !bookData.containsKey("jumlahHalaman") ||
            !bookData.containsKey("fotoCover") ||
            !bookData.containsKey("kategori") ||
            !bookData.containsKey("rating") ||
            !bookData.containsKey("tanggalTerbit")
        ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Data buku tidak lengkap");
        }

        try {
            bookBuilder.reset();
            bookBuilder.setJudulBuku(bookData.get("judulBuku"));
            bookBuilder.setPenulis(bookData.get("penulis"));
            bookBuilder.setPenerbit(bookData.get("penerbit"));
            bookBuilder.setDeskripsi(bookData.get("deskripsi"));
            bookBuilder.setHarga(Double.parseDouble(bookData.get("harga")));
            bookBuilder.setStok(Integer.parseInt(bookData.get("stok")));
            bookBuilder.setIsbn(isbn);
            bookBuilder.setJumlahHalaman(Integer.parseInt(bookData.get("jumlahHalaman")));
            bookBuilder.setFotoCover(bookData.get("fotoCover"));
            bookBuilder.setKategori(bookData.get("kategori"));
            bookBuilder.setRating(Double.parseDouble(bookData.get("rating")));
            bookBuilder.setTanggalTerbit(LocalDate.parse(bookData.get("tanggalTerbit")));
            Book updatedBook = bookService.update(isbn, bookBuilder.getBook());
            return ResponseEntity.ok(updatedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Gagal mengupdate buku, format data tidak valid");
        }
    }

    @DeleteMapping("/book/{isbn}")
    public ResponseEntity<?> deleteBook(
        @PathVariable String isbn
    ) {
        // Check if the book exists
        if (bookService.findByIsbn(isbn) == null) {
            return ResponseEntity.notFound().build();
        }

        bookService.deleteByIsbn(isbn);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<?> getBook(
        @PathVariable String isbn
    ) {
        // Check if the book exists
        if (bookService.findByIsbn(isbn) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookService.findByIsbn(isbn));
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }
}
