package mate.academy.springbootwebgreqit.service;


import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.BookSearchParameters;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;
import mate.academy.springbootwebgreqit.dto.UpdateBookRequestDto;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> criteria_query_branch

import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto update(UpdateBookRequestDto requestDto);

    public List<BookDto> search(BookSearchParameters params);
}
