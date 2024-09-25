package mate.academy.springbootwebgreqit.service;

import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;
import mate.academy.springbootwebgreqit.dto.UpdateBookRequestDto;
import mate.academy.springbootwebgreqit.mapper.BookMapper;
import mate.academy.springbootwebgreqit.model.Book;
import mate.academy.springbootwebgreqit.model.Category;
import mate.academy.springbootwebgreqit.repository.BookRepository;
import mate.academy.springbootwebgreqit.repository.CategoryRepository;
import mate.academy.springbootwebgreqit.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private CategoryRepository categoryRepository;

    private CreateBookRequestDto requestDto;
    private Book book;
    private BookDto bookDto;
    private Category category;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Roman");

        requestDto = new CreateBookRequestDto();
        requestDto.setTitle("wallet");
        requestDto.setAuthor("John");
        requestDto.setIsbn("9283234577892");
        requestDto.setPrice(BigDecimal.valueOf(60.99));
        requestDto.setDescription("Updated description");
        requestDto.setCoverImage("https://example.com/updated-cover-.jpg");
        requestDto.setCategoriesIds(Collections.singletonList(1L));

        book = new Book();
        book.setId(1L);
        book.setTitle("wallet");
        book.setAuthor("John");
        book.setIsbn("9283234577892");
        book.setPrice(BigDecimal.valueOf(60.99));
        book.setDescription("Updated description");
        book.setCoverImage("https://example.com/updated-cover-.jpg");
        book.setCategories(Collections.singleton(category));

        bookDto = new BookDto();
        book.setId(1L);
        bookDto.setTitle("wallet");
        bookDto.setAuthor("John");
        bookDto.setIsbn("9283234577892");
        bookDto.setPrice(BigDecimal.valueOf(60.99));
        bookDto.setDescription("Updated description");
        bookDto.setCoverImage("https://example.com/updated-cover-.jpg");
        bookDto.setCategoriesIds(List.of(1L));
    }

    @Test
    @DisplayName("save() should save a valid book and return BookDto")
    void save_ValidBook_BookDtoReturned() {
        when(bookMapper.toDto(book)).thenReturn(bookDto);
        when(categoryRepository.getReferenceById(1L)).thenReturn(category);
        when(bookMapper.toModel(requestDto)).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        BookDto actualDto = bookService.save(requestDto);

        assertEquals(bookDto, actualDto);
        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("Find book by id")
    void getBook_WithValidId_ShouldReturnValidBook() {
        when(bookMapper.toDto(book)).thenReturn(bookDto);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        BookDto actualDto = bookService.findById(book.getId());

        assertEquals(bookDto, actualDto);
        verify(bookRepository).findById(book.getId());
    }

    @Test
    @DisplayName("Get all books")
    void getAllsBooks_WhatAreInDb_ShouldReturnAllBooks() {
        when(bookMapper.toDto(book)).thenReturn(bookDto);
        when(bookRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(book)));

        Page<BookDto> actualPage = bookService.findAll(PageRequest.of(0, 10));


        assertNotNull(actualPage);
        assertEquals(1, actualPage.getTotalElements());
        assertEquals(1, actualPage.getContent().size());
        assertEquals(bookDto, actualPage.getContent().get(0));
    }

    @Test
    @DisplayName("Delete book by id")
    void DeleteBookById_WithValidBook_ShouldDeleteBookById() {
        Long bookId = book.getId();
        bookService.deleteById(bookId);

        verify(bookRepository).deleteById(bookId);
    }

    @Test
    @DisplayName("Update book")
    void UpdateBook_WithValidBook_ShouldUpdateDataInBook() {
        UpdateBookRequestDto updateRequestDto = new UpdateBookRequestDto();
        updateRequestDto.setId(book.getId());
        updateRequestDto.setTitle("updated title");
        updateRequestDto.setAuthor("updated author");
        updateRequestDto.setIsbn("updated isbn");
        updateRequestDto.setPrice(BigDecimal.valueOf(70.99));
        updateRequestDto.setDescription("Updated description");
        updateRequestDto.setCoverImage("https://example.com/updated-cover.jpg");

        BookDto updatedBookDto = new BookDto();
        updatedBookDto.setId(book.getId());
        updatedBookDto.setTitle("updated title");
        updatedBookDto.setAuthor("updated author");
        updatedBookDto.setIsbn("updated isbn");
        updatedBookDto.setPrice(BigDecimal.valueOf(70.99));
        updatedBookDto.setDescription("Updated description");
        updatedBookDto.setCoverImage("https://example.com/updated-cover.jpg");
        updatedBookDto.setCategoriesIds(List.of(1L));

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        doReturn(updatedBookDto).when(bookMapper).toDto(book);

        BookDto actualDto = bookService.update(updateRequestDto);

        assertEquals(updatedBookDto, actualDto);
        verify(bookRepository).findById(book.getId());
        verify(bookRepository).save(book);
    }
}