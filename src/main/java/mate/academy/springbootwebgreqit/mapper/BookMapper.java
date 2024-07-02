package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.config.MapperConfig;
import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;
import mate.academy.springbootwebgreqit.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel (CreateBookRequestDto requestDto);
}
