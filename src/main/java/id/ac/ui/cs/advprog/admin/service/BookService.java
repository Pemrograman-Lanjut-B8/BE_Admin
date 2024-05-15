package id.ac.ui.cs.advprog.admin.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import id.ac.ui.cs.advprog.admin.model.Book;

public interface BookService {
    CompletableFuture<Book> createBook(Book book);
    CompletableFuture<List<Book>> findAll();
    CompletableFuture<Book> findByIsbn(String isbn);
    CompletableFuture<Book> update(String isbn, Book newBook);
    CompletableFuture<Void> deleteByIsbn(String isbn);
}