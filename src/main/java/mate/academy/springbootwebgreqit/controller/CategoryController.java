package mate.academy.springbootwebgreqit.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebgreqit.dto.BookDtoWithoutCategotyIds;
import mate.academy.springbootwebgreqit.dto.category.CategoryDto;
import mate.academy.springbootwebgreqit.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public CategoryDto createCategory(CategoryDto categoryDto){
        return categoryService.save(categoryDto);
    }

    @GetMapping
    public List<CategoryDto> getAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(Long id){
        return categoryService.getById(id);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto){
        return categoryService.update(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(Long id){
        categoryService.deleteById(id);
    }

    @GetMapping
    public List<BookDtoWithoutCategotyIds> getBooksByCategoryId(Long id){
        return categoryService.findBooksByCategoryId(id);
    }
}
