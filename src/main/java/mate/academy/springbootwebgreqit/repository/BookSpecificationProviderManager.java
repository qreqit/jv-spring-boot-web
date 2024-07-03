package mate.academy.springbootwebgreqit.repository;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.model.Book;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find correct specification provader for key" + key));
    }
}
