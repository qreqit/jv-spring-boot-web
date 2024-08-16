package mate.academy.springbootwebgreqit.repository;

import mate.academy.springbootwebgreqit.model.Book;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    public String getKey();

    public Specification<T> getSpecification(String[] params);
}
