package mate.academy.springbootwebgreqit.mapper;

import mate.academy.springbootwebgreqit.config.MapperComfig;
import mate.academy.springbootwebgreqit.dto.BookDto;
import mate.academy.springbootwebgreqit.dto.CreateBookRequestDto;
import mate.academy.springbootwebgreqit.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperComfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel (CreateBookRequestDto requestDto);
}
