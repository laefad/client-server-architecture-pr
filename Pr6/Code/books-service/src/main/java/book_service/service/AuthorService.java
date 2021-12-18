package book_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book_service.model.Author;
import book_service.repository.AuthorRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorService {
    
    @Autowired
    AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Author getAuthorById(long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    public Author deleteAuthorById(long id) {
        Author author = getAuthorById(id);

        if (author == null)
            return null; // TODO add error
        
        authorRepository.delete(author);
        return author;
    }

}
