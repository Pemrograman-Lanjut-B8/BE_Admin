package id.ac.ui.cs.advprog.admin.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.model.builders.BookBuilderImpl;
import id.ac.ui.cs.advprog.admin.service.BookService;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    BookService bookService;

    @InjectMocks
    AdminController adminController;

    @Mock
    private BookBuilderImpl bookBuilder;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void testAdminFrontPage() throws Exception {
        mockMvc.perform(get("/api/admin/"))
            .andExpect(content().string("<h1>Welcome to Admin Page</h1>"));
    }

    @Test
    public void testAddBook_Success() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(null);
        Book expectedBook = new Book();
        when(bookBuilder.getBook()).thenReturn(expectedBook);

        mockMvc.perform(post("/api/admin/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"judulBuku\":\"Judul Buku\",\"penulis\":\"Penulis\",\"penerbit\":\"Penerbit\",\"tanggalTerbit\":\"2020-01-01\",\"deskripsi\":\"Deskripsi\",\"harga\":100000.0,\"stok\":10,\"isbn\":\"1234567890\",\"jumlahHalaman\":100,\"fotoCover\":\"foto.jpg\",\"kategori\":\"Kategori\",\"rating\":4.5}"))
                .andExpect(status().isCreated());

        verify(bookService, times(1)).createBook(expectedBook);
    }

    @Test
    public void testAddBook_Failed() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(new Book());

        mockMvc.perform(post("/api/admin/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"judulBuku\":\"Judul Buku\",\"penulis\":\"Penulis\",\"penerbit\":\"Penerbit\",\"tanggalTerbit\":\"2020-01-01\",\"deskripsi\":\"Deskripsi\",\"harga\":100000.0,\"stok\":10,\"isbn\":\"1234567890\",\"jumlahHalaman\":100,\"fotoCover\":\"foto.jpg\",\"kategori\":\"Kategori\",\"rating\":4.5}"))
                .andExpect(status().isConflict());
    }

    @Test
    public void testUpdateBook_Success() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(new Book());

        mockMvc.perform(put("/api/admin/book/1234567890")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"judulBuku\":\"Judul Buku\",\"penulis\":\"Penulis\",\"penerbit\":\"Penerbit\",\"tanggalTerbit\":\"2020-01-01\",\"deskripsi\":\"Deskripsi\",\"harga\":100000.0,\"stok\":10,\"isbn\":\"1234567890\",\"jumlahHalaman\":100,\"fotoCover\":\"foto.jpg\",\"kategori\":\"Kategori\",\"rating\":4.5}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateBook_Failed() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(null);

        mockMvc.perform(put("/api/admin/book/1234567890")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"judulBuku\":\"Judul Buku\",\"penulis\":\"Penulis\",\"penerbit\":\"Penerbit\",\"tanggalTerbit\":\"2020-01-01\",\"deskripsi\":\"Deskripsi\",\"harga\":100000.0,\"stok\":10,\"isbn\":\"1234567890\",\"jumlahHalaman\":100,\"fotoCover\":\"foto.jpg\",\"kategori\":\"Kategori\",\"rating\":4.5}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteBook() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(new Book());

        mockMvc.perform(delete("/api/admin/book/1234567890"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBook_Failed() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(null);

        mockMvc.perform(delete("/api/admin/book/1234567890"))
                .andExpect(status().isNotFound());
    }
}