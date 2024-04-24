package id.ac.ui.cs.advprog.admin.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.createBook(book);
    }

    @Override
    public List<Book> findAll() {
        Iterator<Book> booksIterator = bookRepository.findAll();
        List<Book> bookList = new ArrayList<>();
        booksIterator.forEachRemaining(bookList::add);
        return bookList;
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Book update(String isbn, Book newBook) {
        return bookRepository.update(isbn, newBook);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        bookRepository.delete(isbn);
    }
}
