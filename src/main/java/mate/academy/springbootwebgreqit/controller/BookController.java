package mate.academy.springbootwebgreqit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.BookSearchParameters;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;
import mate.academy.springbootwebgreqit.dto.UpdateBookRequestDto;
import mate.academy.springbootwebgreqit.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public  class BookController {
    private final BookService bookService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@Valid @PathVariable Long id) {
        return bookService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public BookDto createBook(@Valid @RequestBody CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookDto updateBook(@RequestBody UpdateBookRequestDto updateBookRequestDto) {
        return bookService.update(updateBookRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @GetMapping("/search")
    public List<BookDto> searchBooks(BookSearchParameters searchParameters) {
        return bookService.search(searchParameters);
    }
}
