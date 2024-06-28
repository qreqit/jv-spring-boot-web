package mate.academy.springbootwebgreqit.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;
import mate.academy.springbootwebgreqit.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value =  "/books")
public  class BookController {
    private BookService bookService;

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public BookDto createBook(@RequestBody CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }
}