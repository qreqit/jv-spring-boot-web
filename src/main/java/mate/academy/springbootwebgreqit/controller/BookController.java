package mate.academy.springbootwebgreqit.controller;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.BookSearchParameters;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;
import mate.academy.springbootwebgreqit.dto.UpdateBookRequestDto;
import mate.academy.springbootwebgreqit.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    @ApiOperation(value = "get all books with pagination")
    public Page<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get book by id")
    public BookDto getBookById(@Valid @PathVariable Long id) {
        return bookService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ApiOperation(value = "create a book")
    public BookDto createBook(@Valid @RequestBody CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public BookDto updateBook(@RequestBody UpdateBookRequestDto updateBookRequestDto) {
        return bookService.update(updateBookRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete a book")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/search")
    @ApiOperation(value = "delete a book")
    public Page<BookDto> searchBooks(BookSearchParameters searchParameters, Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }
}
