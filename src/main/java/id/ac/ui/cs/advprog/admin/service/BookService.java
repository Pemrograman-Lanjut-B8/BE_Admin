package id.ac.ui.cs.advprog.admin.service;

import java.util.List;

import id.ac.ui.cs.advprog.admin.model.Book;

public interface BookService {
    public Book createBook(Book book);
    public List<Book> findAll();
    Book findByIsbn(String isbn);
    public Book update(String isbn, Book newBook);
    public void deleteByIsbn(String isbn);
}
