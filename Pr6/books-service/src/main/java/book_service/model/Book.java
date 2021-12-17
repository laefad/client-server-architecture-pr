package book_service.model;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "book")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"authors", "users", "tagsMap"})
public class Book {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    long id;

    @Column(nullable = false)
    String name;

    @Column
    String description;

    @Column
    @Temporal(TemporalType.DATE)
    Date publicationDate;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    @JsonIgnoreProperties({"books"})
    Set<Author> authors;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    @JsonIgnoreProperties({"books", "tagsMap"})
    Set<UserBookDetails> users;

    @ElementCollection
    @MapKeyJoinColumn(name = "username")
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    Map<UserBookDetails, TagList> tagsMap;

    @OneToMany(fetch = FetchType.LAZY)
    Set<Tag> tags;
}
