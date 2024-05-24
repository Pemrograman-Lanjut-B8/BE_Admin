package id.ac.ui.cs.advprog.admin.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.service.BookService;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testBookFrontPage() throws Exception {
        mockMvc.perform(get("/api/book/"))
               .andExpect(status().isOk())
               .andExpect(content().string("<h1>Welcome to Book Page</h1>"));
    }

    @Test
    public void testGetBook_NotFound() throws Exception {
        String isbn = "1234567890"; 

        when(bookService.findByIsbn(isbn)).thenReturn(null);

        mockMvc.perform(get("/api/book/{isbn}", isbn))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.status", is("error")))
               .andExpect(jsonPath("$.message", is("Book not found")));
    }

    @Test
    public void testGetBook_Found() throws Exception {
        String isbn = "1234567890"; 
        Book book = new Book(); 

        when(bookService.findByIsbn(isbn)).thenReturn(book);

        mockMvc.perform(get("/api/book/{isbn}", isbn))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status", is("success")))
               .andExpect(jsonPath("$.message", is("Book found")))
               .andExpect(jsonPath("$.data", notNullValue())); 
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> bookList = new ArrayList<>();
        when(bookService.findAll()).thenReturn(bookList);
        mockMvc.perform(get("/api/book/all"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status", is("success")))
               .andExpect(jsonPath("$.message", is("All books")))
               .andExpect(jsonPath("$.data", notNullValue()))
               .andExpect(jsonPath("$.data", hasSize(bookList.size())));
    }
}
