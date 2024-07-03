package mate.academy.springbootwebgreqit.service.impl;


import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;
import mate.academy.springbootwebgreqit.dto.UpdateBookRequestDto;
import mate.academy.springbootwebgreqit.exception.EntityNotFoundException;
import mate.academy.springbootwebgreqit.mapper.BookMapper;
import mate.academy.springbootwebgreqit.model.Book;
import mate.academy.springbootwebgreqit.repository.BookRepository;
import mate.academy.springbootwebgreqit.service.BookService;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find book by id:" + id));
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto update(UpdateBookRequestDto requestDto) {
        Book book = bookRepository.findById(requestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id" + requestDto.getId()));
        bookMapper.updateBookFromDto(requestDto, book);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDto(updatedBook);
    }
}
