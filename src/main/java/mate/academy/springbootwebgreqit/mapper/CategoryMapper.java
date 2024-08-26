package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.category.CategoryDto;
import mate.academy.springbootwebgreqit.dto.category.CreateCategoryRequestDto;
import mate.academy.springbootwebgreqit.dto.category.UpdateCategoryRequestDto;
import mate.academy.springbootwebgreqit.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

<<<<<<< HEAD
    Category toEntity(CreateCategoryRequestDto requestDto);
=======
    Category toEntity(CategoryDto categoryDto);
>>>>>>> bad5ce46860cb6aefcf2e0dfce281358a6c03250

    void updateCategoryFromDto(UpdateCategoryRequestDto categoryDto, @MappingTarget Category entity);
}
