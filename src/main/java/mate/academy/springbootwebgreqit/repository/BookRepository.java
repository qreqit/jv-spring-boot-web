package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

}
