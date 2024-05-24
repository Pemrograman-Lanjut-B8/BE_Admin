package id.ac.ui.cs.advprog.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import id.ac.ui.cs.advprog.admin.dto.ResponseDto;
import id.ac.ui.cs.advprog.admin.service.BookService;

@RestController
@RequestMapping("/api/book/")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    @ResponseBody
    public String bookFrontPage() {
        return "<h1>Welcome to Book Page</h1>";
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/{isbn}")
    public ResponseEntity<ResponseDto> getBook(@PathVariable("isbn") String isbn) {
        if (bookService.findByIsbn(isbn) == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    new ResponseDto<>("error", "Book not found", null)
                );
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                new ResponseDto<>("success", "Book found", bookService.findByIsbn(isbn))
            );
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllBooks() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                new ResponseDto<>("success", "All books", bookService.findAll())
            );
    }
}
