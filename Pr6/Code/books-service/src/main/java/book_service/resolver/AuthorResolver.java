package book_service.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import book_service.model.Author;
import book_service.model.Book;
import book_service.model.input.author.AuthorCreateInput;
import book_service.service.AuthorService;
import book_service.service.BookService;

import java.util.Objects;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AuthorResolver {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;
    
    @QueryMapping
    public List<Author> getAllAuthors() {
        return authorService.getAll();
    }

    @QueryMapping
    public Author getAuthor(@Argument long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @MutationMapping
    public Author createAuthor(@Argument AuthorCreateInput input) {

        Set<Book> books = new HashSet<>();

        if (input.getBookIds() != null)
            books = input.getBookIds().stream()
                    .map(bookService::getBookById) //TODO add checks ?
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

        Author author = Author.builder()
                              .name(input.getName())
                              .surname(input.getSurname())
                              .middlename(input.getMiddlename())
                              .birthDate(input.getBirthDate())
                              .deathDate(input.getDeathDate())
                              .books(books)
                              .build();
        
        return authorService.saveAuthor(author);
    }

    @MutationMapping
    public Author deleteAuthor(@Argument long authorId) {
        return authorService.deleteAuthorById(authorId);
    }
}
