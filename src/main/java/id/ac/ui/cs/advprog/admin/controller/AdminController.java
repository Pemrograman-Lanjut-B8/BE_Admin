package id.ac.ui.cs.advprog.admin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import id.ac.ui.cs.advprog.admin.dto.BookDto;
import id.ac.ui.cs.advprog.admin.dto.ResponseDto;
import id.ac.ui.cs.advprog.admin.dto.UserDto;
import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.model.UserEntity;
import id.ac.ui.cs.advprog.admin.model.builders.BookBuilderImpl;
import id.ac.ui.cs.advprog.admin.service.BookService;
import id.ac.ui.cs.advprog.admin.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookBuilderImpl bookBuilder;

    private static final String ADMIN_TEST_URL = "http://localhost:8081/api/test/admin";

    @GetMapping("/")
    @ResponseBody
    public String adminFrontPage(@RequestHeader("Authorization") String token) {
        ResponseEntity<String> response = testAdmin(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return "Unauthorized";
        }

        return "<h1>Welcome to Admin Page</h1>";
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/book")
    public ResponseEntity<ResponseDto> addBook(@RequestBody BookDto bookDto, @RequestHeader("Authorization") String token) {
        ResponseEntity<String> response = testAdmin(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseDto<>("error", "Unauthorized", null));
        }

        if (bookService.findByIsbn(bookDto.getIsbn()) != null) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                    new ResponseDto<>("error", "Book already exists", null)
                );
        }

        Book book = buildBook(bookDto.getIsbn(), bookDto);
        bookService.createBook(book);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                new ResponseDto<>("success", "Book added successfully", book)
            );
    }

    @SuppressWarnings("rawtypes")
    @PutMapping("/book/{isbn}")
    @Transactional
    public ResponseEntity<ResponseDto> updateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto, @RequestHeader("Authorization") String token) {
        ResponseEntity<String> response = testAdmin(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseDto<>("error", "Unauthorized", null));
        }

        if (bookService.findByIsbn(isbn) == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    new ResponseDto<>("error", "Book not found", null)
                );
        }

        bookService.update(
            isbn,
            bookDto.getJudulBuku(),
            bookDto.getPenulis(),
            bookDto.getPenerbit(),
            bookDto.getDeskripsi(),
            bookDto.getHarga(),
            bookDto.getStok(),
            bookDto.getTanggalTerbit(),
            bookDto.getJumlahHalaman(),
            bookDto.getFotoCover(),
            bookDto.getKategori(),
            bookDto.getRating()
        );

        Book book = bookService.findByIsbn(isbn);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                new ResponseDto<>("success", "Book updated successfully", book)
            );
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/book/{isbn}")
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable("isbn") String isbn, @RequestHeader("Authorization") String token) {
        ResponseEntity<String> response = testAdmin(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseDto<>("error", "Unauthorized", null));
        }

        if (bookService.findByIsbn(isbn) == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    new ResponseDto<>("error", "Book not found", null)
                );
        }

        bookService.deleteByIsbn(isbn);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                new ResponseDto<>("success", "Book deleted successfully", null)
            );
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/users")
    public ResponseEntity<ResponseDto> getAllUsers(@RequestHeader("Authorization") String token) {
        ResponseEntity<String> response = testAdmin(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseDto<>("error", "Unauthorized", null));
        }

        List<UserEntity> users = userService.findAll();
        List<UserDto>  userDtos = users.stream()
                                      .map(user -> new UserDto(user.getUsername(), user.getEmail()))
                                      .collect(Collectors.toList());
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                new ResponseDto<>("success", "Users retrieved successfully", userDtos)
            );
    }

    private Book buildBook(String isbn, BookDto bookData) {
        bookBuilder.reset();
        bookBuilder.setJudulBuku(bookData.getJudulBuku());
        bookBuilder.setPenulis(bookData.getPenulis());
        bookBuilder.setPenerbit(bookData.getPenerbit());
        bookBuilder.setDeskripsi(bookData.getDeskripsi());
        bookBuilder.setHarga(bookData.getHarga());
        bookBuilder.setStok(bookData.getStok());
        bookBuilder.setIsbn(isbn);
        bookBuilder.setJumlahHalaman(bookData.getJumlahHalaman());
        bookBuilder.setFotoCover(bookData.getFotoCover());
        bookBuilder.setKategori(bookData.getKategori());
        bookBuilder.setRating(bookData.getRating());
        bookBuilder.setTanggalTerbit(bookData.getTanggalTerbit());
        return bookBuilder.getBook();
    }

    protected ResponseEntity<String> testAdmin(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            return new RestTemplate().exchange(ADMIN_TEST_URL, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException.Forbidden ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}
