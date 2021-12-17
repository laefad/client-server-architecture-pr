package book_service.model.input.book;

import java.util.Date;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Getter
@Setter
@ToString
public class BookCreateInput {
    String username;
    String name;
    String description;
    Date publicationDate;
    List<Long> authorIds;
    List<Long> tagIds;
}
