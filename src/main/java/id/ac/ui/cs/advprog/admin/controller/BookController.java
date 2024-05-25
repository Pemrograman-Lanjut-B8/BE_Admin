package id.ac.ui.cs.advprog.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import id.ac.ui.cs.advprog.admin.dto.ResponseDto;
import id.ac.ui.cs.advprog.admin.service.BookService;

@RestController
@RequestMapping("/api/book/")
public class BookController {
    @Autowired
    private BookService bookService;

    private static final String USER_TEST_URL = "http://localhost:8081/api/test/user";

    @GetMapping("/")
    @ResponseBody
    public String bookFrontPage(@RequestHeader("Authorization") String token) {
        ResponseEntity<String> response = testUser(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return "Unauthorized";
        }

        return "<h1>Welcome to Book Page</h1>";
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/{isbn}")
    public ResponseEntity<ResponseDto> getBook(@PathVariable("isbn") String isbn, @RequestHeader("Authorization") String token) {
        ResponseEntity<String> response = testUser(token);

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

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                new ResponseDto<>("success", "Book found", bookService.findByIsbn(isbn))
            );
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllBooks(@RequestHeader("Authorization") String token) {
        ResponseEntity<String> response = testUser(token);

        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseDto<>("error", "Unauthorized", null));
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                new ResponseDto<>("success", "All books", bookService.findAll())
            );
    }

    protected ResponseEntity<String> testUser(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            return new RestTemplate().exchange(USER_TEST_URL, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException.Forbidden ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}
