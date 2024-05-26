
package id.ac.ui.cs.advprog.admin.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.model.UserEntity;
import id.ac.ui.cs.advprog.admin.model.builders.BookBuilderImpl;
import id.ac.ui.cs.advprog.admin.service.BookService;
import id.ac.ui.cs.advprog.admin.service.UserService;

import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    BookService bookService;

    @Mock
    UserService userService;

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
        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("OK", HttpStatus.OK)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(get("/api/admin/")
                        .header("Authorization", "Bearer testToken"))
                .andExpect(status().isOk())
                .andExpect(content().string("<h1>Welcome to Admin Page</h1>"));
    }

    @Test
    public void testAdminFrontPage_Unauthorized() throws Exception {
        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(get("/api/admin/")
                        .header("Authorization", "Bearer testToken"))
                .andExpect(content().string("Unauthorized"));
    }

    @Test
    public void testAddBook_Success() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(null);
        Book expectedBook = new Book();
        when(bookBuilder.getBook()).thenReturn(expectedBook);

        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("OK", HttpStatus.OK)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(post("/api/admin/book")
                        .header("Authorization", "Bearer testToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"judulBuku\":\"Judul Buku\",\"penulis\":\"Penulis\",\"penerbit\":\"Penerbit\",\"tanggalTerbit\":\"2020-01-01\",\"deskripsi\":\"Deskripsi\",\"harga\":100000.0,\"stok\":10,\"isbn\":\"1234567890\",\"jumlahHalaman\":100,\"fotoCover\":\"foto.jpg\",\"kategori\":\"Kategori\",\"rating\":4.5}"))
                .andExpect(status().isCreated());

        verify(bookService, times(1)).createBook(expectedBook);
    }

    @Test
    public void testAddBook_Unauthorized() throws Exception {
        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(post("/api/admin/book")
                        .header("Authorization", "Bearer testToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"judulBuku\":\"Judul Buku\",\"penulis\":\"Penulis\",\"penerbit\":\"Penerbit\",\"tanggalTerbit\":\"2020-01-01\",\"deskripsi\":\"Deskripsi\",\"harga\":100000.0,\"stok\":10,\"isbn\":\"1234567890\",\"jumlahHalaman\":100,\"fotoCover\":\"foto.jpg\",\"kategori\":\"Kategori\",\"rating\":4.5}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testAddBook_Failed() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(new Book());

        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("OK", HttpStatus.OK)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(post("/api/admin/book")
                        .header("Authorization", "Bearer testToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"judulBuku\":\"Judul Buku\",\"penulis\":\"Penulis\",\"penerbit\":\"Penerbit\",\"tanggalTerbit\":\"2020-01-01\",\"deskripsi\":\"Deskripsi\",\"harga\":100000.0,\"stok\":10,\"isbn\":\"1234567890\",\"jumlahHalaman\":100,\"fotoCover\":\"foto.jpg\",\"kategori\":\"Kategori\",\"rating\":4.5}"))
                .andExpect(status().isConflict());
    }

    @Test
    public void testUpdateBook_Success() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(new Book());

        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("OK", HttpStatus.OK)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(put("/api/admin/book/1234567890")
                        .header("Authorization", "Bearer testToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"judulBuku\":\"Judul Buku\",\"penulis\":\"Penulis\",\"penerbit\":\"Penerbit\",\"tanggalTerbit\":\"2020-01-01\",\"deskripsi\":\"Deskripsi\",\"harga\":100000.0,\"stok\":10,\"isbn\":\"1234567890\",\"jumlahHalaman\":100,\"fotoCover\":\"foto.jpg\",\"kategori\":\"Kategori\",\"rating\":4.5}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateBook_Unauthorized() throws Exception {
        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(put("/api/admin/book/1234567890")
                        .header("Authorization", "Bearer testToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"judulBuku\":\"Judul Buku\",\"penulis\":\"Penulis\",\"penerbit\":\"Penerbit\",\"tanggalTerbit\":\"2020-01-01\",\"deskripsi\":\"Deskripsi\",\"harga\":100000.0,\"stok\":10,\"isbn\":\"1234567890\",\"jumlahHalaman\":100,\"fotoCover\":\"foto.jpg\",\"kategori\":\"Kategori\",\"rating\":4.5}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateBook_Failed() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(null);

        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("OK", HttpStatus.OK)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(put("/api/admin/book/1234567890")
                        .header("Authorization", "Bearer testToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"judulBuku\":\"Judul Buku\",\"penulis\":\"Penulis\",\"penerbit\":\"Penerbit\",\"tanggalTerbit\":\"2020-01-01\",\"deskripsi\":\"Deskripsi\",\"harga\":100000.0,\"stok\":10,\"isbn\":\"1234567890\",\"jumlahHalaman\":100,\"fotoCover\":\"foto.jpg\",\"kategori\":\"Kategori\",\"rating\":4.5}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteBook_Unauthorized() throws Exception {
        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(delete("/api/admin/book/1234567890")
                        .header("Authorization", "Bearer testToken"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteBook() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(new Book());

        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("OK", HttpStatus.OK)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(delete("/api/admin/book/1234567890")
                        .header("Authorization", "Bearer testToken"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBook_Failed() throws Exception {
        when(bookService.findByIsbn(any())).thenReturn(null);

        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("OK", HttpStatus.OK)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(delete("/api/admin/book/1234567890")
                        .header("Authorization", "Bearer testToken"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllUsers_Mapping() throws Exception {
        List<UserEntity> userList = new ArrayList<>();
        userList.add(new UserEntity("username1", "email1@example.com", "password1"));
        userList.add(new UserEntity("username2", "email2@example.com", "password2"));
        when(userService.findAll()).thenReturn(userList);

        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("OK", HttpStatus.OK)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(get("/api/admin/users")
                        .header("Authorization", "Bearer testToken"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Users retrieved successfully"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].username").value("username1"))
                .andExpect(jsonPath("$.data[0].email").value("email1@example.com"))
                .andExpect(jsonPath("$.data[1].username").value("username2"))
                .andExpect(jsonPath("$.data[1].email").value("email2@example.com"));
    }

    @Test
    public void testGetAllUsers_Unauthorized() throws Exception {
        AdminController adminControllerSpy = spy(adminController);
        doReturn(new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED)).when(adminControllerSpy).testAdmin(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(adminControllerSpy).build();

        mockMvc.perform(get("/api/admin/users")
                        .header("Authorization", "Bearer testToken"))
                .andExpect(status().isUnauthorized());
    }
}
