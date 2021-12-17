package book_service.model.input.book;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Getter
@Setter
@ToString
public class BookTagAddInput {
    long bookId;
    long tagId;
}
