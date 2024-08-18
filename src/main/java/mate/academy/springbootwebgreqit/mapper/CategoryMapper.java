package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.category.CategoryDto;
import mate.academy.springbootwebgreqit.dto.category.UpdateCategoryRequestDto;
import mate.academy.springbootwebgreqit.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(source = "description", target = "description")
    CategoryDto toDto(Category category);

    @Mapping(source = "description", target = "description")
    Category toEntity(CategoryDto categoryDTO);


    void updateCategoryFromDto(UpdateCategoryRequestDto categoryDto, @MappingTarget Category entity);
}
