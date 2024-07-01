package mate.academy.springbootwebgreqit.service;


import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.BookSearchParameters;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;

import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    public BookDto findById(Long id);

    public void deleteById(Long id);

    public BookDto update(Long id);

    public List<BookDto> search(BookSearchParameters params);
}
