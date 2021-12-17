package book_service.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import book_service.model.UserBookDetails;
import book_service.model.input.book.BookAddToUserInput;
import book_service.service.UserBookDetailsService;

@Controller
public class UserBookDetailsResolver {

    @Autowired
    UserBookDetailsService userBookDetailsService;
    
    @QueryMapping
    public List<UserBookDetails> getAllUserBookDetails() {
        return userBookDetailsService.getAll();
    }

    @QueryMapping
    public UserBookDetails getUserBookDetails(@Argument String username) {
        return userBookDetailsService.getUserBookDetailsByUsername(username);
    }

    @MutationMapping
    public UserBookDetails deleteUserBookDetails(@Argument String username) {
        return userBookDetailsService.removeByUsername(username);
    }

    @MutationMapping
    public UserBookDetails addBookToUser(@Argument BookAddToUserInput input) {
        return userBookDetailsService.addBook(
            input.getUsername(), 
            input.getBookId()
        );
    }

    @MutationMapping
    public UserBookDetails removeBookFromUser(@Argument BookAddToUserInput input) {
        return userBookDetailsService.removeBook(
            input.getUsername(), 
            input.getBookId()
        );
    }
    
}
