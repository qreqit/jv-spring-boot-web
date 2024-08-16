package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.dto.BookSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParameters searchParameters);
}
