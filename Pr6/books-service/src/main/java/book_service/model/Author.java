package book_service.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "author")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"books"})
public class Author {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String surname;

    @Column
    String middlename;

    @Column
    String description;

    @Column
    @Temporal(TemporalType.DATE)
    Date birthDate;

    @Column
    @Temporal(TemporalType.DATE)
    Date deathDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"tagsMap", "users", "authors"})
    Set<Book> books;
}
