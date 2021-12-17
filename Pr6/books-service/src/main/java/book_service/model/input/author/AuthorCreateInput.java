package book_service.model.input.author;

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
public class AuthorCreateInput {
    String name;
    String surname;
    String middlename;
    String description;
    Date birthDate;
    Date deathDate;
    List<Long> bookIds;
}
