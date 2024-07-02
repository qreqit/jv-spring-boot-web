package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
