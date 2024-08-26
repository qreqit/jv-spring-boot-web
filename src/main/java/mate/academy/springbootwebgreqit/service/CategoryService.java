package mate.academy.springbootwebgreqit.service;

import mate.academy.springbootwebgreqit.dto.BookDtoWithoutCategotyIds;
import mate.academy.springbootwebgreqit.dto.category.CategoryDto;
import mate.academy.springbootwebgreqit.dto.category.CreateCategoryRequestDto;
import mate.academy.springbootwebgreqit.dto.category.UpdateCategoryRequestDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();

    CategoryDto getById(Long id);

    CategoryDto save(CreateCategoryRequestDto categoryDto);

    CategoryDto update(Long id, UpdateCategoryRequestDto categoryDto);

    void deleteById(Long id);

    List<BookDtoWithoutCategotyIds> findBooksByCategoryId(Long categotyId);
}
