package mate.academy.springbootwebgreqit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.BookDtoWithoutCategotyIds;
import mate.academy.springbootwebgreqit.dto.category.CategoryDto;
import mate.academy.springbootwebgreqit.dto.category.CreateCategoryRequestDto;
import mate.academy.springbootwebgreqit.dto.category.UpdateCategoryRequestDto;
import mate.academy.springbootwebgreqit.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public CategoryDto createCategory(@Valid @RequestBody CreateCategoryRequestDto categoryDto){
        return categoryService.save(categoryDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<CategoryDto> getAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@Valid @PathVariable Long id){
        return categoryService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public CategoryDto updateCategory(Long id, @RequestBody UpdateCategoryRequestDto categoryDto){
        return categoryService.update(id, categoryDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
    }

    @GetMapping("/{id}/books")
    public List<BookDtoWithoutCategotyIds> getBooksByCategoryId(Long id){
        return categoryService.findBooksByCategoryId(id);
    }
}
