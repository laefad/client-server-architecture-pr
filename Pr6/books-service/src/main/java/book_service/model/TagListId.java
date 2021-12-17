package book_service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TagListId implements Serializable {
    @Column
    String username;
    @Column
    long bookId;
}
