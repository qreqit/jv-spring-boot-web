package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.config.MapperConfig;
import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;
import mate.academy.springbootwebgreqit.dto.UpdateBookRequestDto;
import mate.academy.springbootwebgreqit.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    void updateBookFromDto(UpdateBookRequestDto book, @MappingTarget Book entity);

    Book toModel (CreateBookRequestDto requestDto);
}
