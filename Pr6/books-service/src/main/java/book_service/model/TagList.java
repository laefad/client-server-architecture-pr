package book_service.model;

import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tagList")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"user", "book"})
public class TagList {

    @EmbeddedId
    TagListId tagListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("username")
    @JoinColumn(name = "username")
    @JsonIgnoreProperties({"books", "tagsMap"})
    UserBookDetails user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    @JoinColumn(name = "bookId")
    @JsonIgnoreProperties({"tagsMap", "users", "authors"})
    Book book;

    @OneToMany(fetch = FetchType.LAZY)
    Set<Tag> tags;
}
