package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.config.MapperConfig;
import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.BookDtoWithoutCategotyIds;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;
import mate.academy.springbootwebgreqit.dto.UpdateBookRequestDto;
import mate.academy.springbootwebgreqit.exception.EntityNotFoundException;
import mate.academy.springbootwebgreqit.model.Book;
import mate.academy.springbootwebgreqit.repository.BookRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;


@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    void updateBookFromDto(UpdateBookRequestDto book, @MappingTarget Book entity);

    Book toModel(CreateBookRequestDto requestDto);

    BookDtoWithoutCategotyIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoriyIds(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategoriesIds(book.getCategories().stream()
                .map(category -> category.getId())
                .collect(Collectors.toList()));
    }
}
