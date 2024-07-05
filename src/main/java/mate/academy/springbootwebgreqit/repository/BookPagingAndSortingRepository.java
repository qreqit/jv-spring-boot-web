package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookPagingAndSortingRepository extends PagingAndSortingRepository<Book, Long> {
    @Override
    Iterable<Book> findAll(Sort sort);

    @Override
    Page<Book> findAll(Pageable pageable);
}
