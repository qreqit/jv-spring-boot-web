package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.dto.category.CategoryDto;
import mate.academy.springbootwebgreqit.dto.category.UpdateCategoryRequestDto;
import mate.academy.springbootwebgreqit.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CategoryDto toDto(Category category);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Category toEntity(CategoryDto categoryDTO);

    void updateCategoryFromDto(UpdateCategoryRequestDto book, @MappingTarget Category entity);
}
