package book_service.model;

import java.util.Map;
import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "userBookDetails")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"books", "tagsMap"})
public class UserBookDetails {

    @Id
    @Column(nullable = false, unique = true)
    String username;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"authors", "users", "tagsMap"})
    Set<Book> books;

    @ElementCollection
    @MapKeyJoinColumn(name = "bookId")
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    Map<Book, TagList> tagsMap;
}
