package id.ac.ui.cs.advprog.admin.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.admin.model.Book;
import id.ac.ui.cs.advprog.admin.model.builders.BookBuilderImpl;

@Component
@Repository
public class BookRepository {
    private List<Book> booksData = new ArrayList<>();

    @Autowired
    private BookBuilderImpl bookBuilder;

    public Book createBook(Book book) {
        booksData.add(book);
        return book;
    }

    public Iterator<Book> findAll() {
        return booksData.iterator();
    }

    public Book update(
        String isbn,
        Book newBook
    ) {
        for (int i = 0; i < booksData.size(); i++) {
            Book book = booksData.get(i);
            if (book.getIsbn().equals(isbn)) {
                bookBuilder.reset();
                bookBuilder.setBook(newBook);
                Book updatedBook = bookBuilder.getBook();
                booksData.set(i, updatedBook);
                return updatedBook;
            }
        }

        return null;
    }

    public void delete(String isbn) {
        booksData.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public Book findByIsbn(String isbn) {
        for (Book book : booksData) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        return null;
    }
}
