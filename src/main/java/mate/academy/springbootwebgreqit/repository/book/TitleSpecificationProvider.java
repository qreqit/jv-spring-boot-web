package mate.academy.springbootwebgreqit.repository.book;

import mate.academy.springbootwebgreqit.model.Book;
import mate.academy.springbootwebgreqit.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "title";
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get("title").in(Arrays
                .stream(params)
                .toArray());
    }
}
