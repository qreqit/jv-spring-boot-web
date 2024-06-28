package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    List<Book> findAll();

    Optional<Book> findById(Long id);
}
