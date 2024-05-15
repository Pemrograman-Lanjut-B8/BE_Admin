package id.ac.ui.cs.advprog.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.admin.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String>{
    @SuppressWarnings("null")
    List<Book> findAll();
    Book findByIsbn(String isbn);
}
