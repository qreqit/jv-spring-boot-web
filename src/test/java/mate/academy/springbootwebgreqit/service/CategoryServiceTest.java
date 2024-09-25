package mate.academy.springbootwebgreqit.service;

import mate.academy.springbootwebgreqit.dto.BookDtoWithoutCategotyIds;
import mate.academy.springbootwebgreqit.dto.category.CategoryDto;
import mate.academy.springbootwebgreqit.dto.category.CreateCategoryRequestDto;
import mate.academy.springbootwebgreqit.dto.category.UpdateCategoryRequestDto;
import mate.academy.springbootwebgreqit.mapper.BookMapper;
import mate.academy.springbootwebgreqit.mapper.CategoryMapper;
import mate.academy.springbootwebgreqit.model.Book;
import mate.academy.springbootwebgreqit.model.Category;
import mate.academy.springbootwebgreqit.repository.BookRepository;
import mate.academy.springbootwebgreqit.repository.CategoryRepository;
import mate.academy.springbootwebgreqit.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
@Mock
    private CategoryRepository categoryRepository;
@Mock
    private CategoryMapper categoryMapper;
@Mock
    private BookRepository bookRepository;
@Mock
    private BookMapper bookMapper;

    private Book book;
    private Category category;
    private CreateCategoryRequestDto createCategoryRequestDto;
    private CategoryDto categoryDto;
    private UpdateCategoryRequestDto updateCategoryRequestDto;
    private BookDtoWithoutCategotyIds withoutCategotyIds;

@InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Roman");

        createCategoryRequestDto = new CreateCategoryRequestDto();
        createCategoryRequestDto.setName("Roman");
        createCategoryRequestDto.setDescription("Some description");

        categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("Roman");
        categoryDto.setDescription("Some description");

        updateCategoryRequestDto = new UpdateCategoryRequestDto();
        updateCategoryRequestDto.setId(1L);
        updateCategoryRequestDto.setName("Horror");
        updateCategoryRequestDto.setDescription("Updated description");

        book = new Book();
        book.setId(1L);
        book.setTitle("wallet");
        book.setAuthor("John");
        book.setIsbn("9283234577892");
        book.setPrice(BigDecimal.valueOf(60.99));
        book.setDescription("Updated description");
        book.setCoverImage("https://example.com/updated-cover-.jpg");
        book.setCategories(Collections.singleton(category));

        withoutCategotyIds = new BookDtoWithoutCategotyIds();
        withoutCategotyIds.setId(1L);
        withoutCategotyIds.setTitle("wallet");
        withoutCategotyIds.setAuthor("John");
        withoutCategotyIds.setIsbn("9283234577892");
        withoutCategotyIds.setPrice(BigDecimal.valueOf(60.99));
        withoutCategotyIds.setDescription("Updated description");
        withoutCategotyIds.setCoverImage("https://example.com/updated-cover-.jpg");
    }

    @Test
    @DisplayName("save() should save a valid category and return CategoryDto")
    void save_ValidCategory_CategoryDtoReturned() {
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);
        when(categoryMapper.toEntity(createCategoryRequestDto)).thenReturn(category);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CategoryDto actualDto = categoryService.save(createCategoryRequestDto);

        assertEquals(categoryDto, actualDto);
        verify(categoryRepository).save(category);
    }

    @Test
    @DisplayName("Find category by id")
    void getCategory_WithValidId_ShouldReturnValidCategory() {
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        CategoryDto actualDto = categoryService.getById(category.getId());

        assertEquals(categoryDto, actualDto);
        verify(categoryRepository).findById(category.getId());
    }

    @Test
    @DisplayName("Get all categories")
    void getAllsCategories_WhatAreInDb_ShouldReturnAllCategories() {
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<CategoryDto> actualDto = categoryService.findAll();

        assertEquals(List.of(categoryDto), actualDto);
        verify(categoryRepository).findAll();
    }

    @Test
    @DisplayName("Delete category by id")
    void DeleteCategoryById_WithValidCategory_ShouldDeleteCategoryById() {
        categoryService.deleteById(category.getId());

        verify(categoryRepository).deleteById(category.getId());
    }

    @Test
    @DisplayName("Update category")
    void UpdateCategory_WithValidCategory_ShouldUpdateDataInCategory() {
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        doReturn(categoryDto).when(categoryMapper).toDto(category);

        CategoryDto actualDto = categoryService.update(updateCategoryRequestDto);

        assertEquals(categoryDto, actualDto);

        verify(categoryRepository).findById(category.getId());
        verify(categoryRepository).save(category);
    }

    @Test
    @DisplayName("Find books by category id")
    void FindBooks_WithValidCategory_ByCategoryId() {
        when(bookRepository.findByCategories_Id(category.getId())).thenReturn(List.of(book));
        when(bookMapper.toDtoWithoutCategories(book)).thenReturn(withoutCategotyIds);

        List<BookDtoWithoutCategotyIds> actualDto = categoryService.findBooksByCategoryId(category.getId());

        assertEquals(List.of(withoutCategotyIds), actualDto);
        verify(bookRepository).findByCategories_Id(category.getId());
    }
}
