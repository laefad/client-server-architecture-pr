package book_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book_service.model.Author;
import book_service.model.Book;
import book_service.model.Tag;
import book_service.repository.BookRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookService {
    
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    TagService tagService;

    @Transactional(readOnly = true)
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book deleteBookById(long id) {
        Book book = getBookById(id);

        if (book == null)
            return null; // TODO add error

        bookRepository.delete(book);
        return book;
    }

    @Transactional
    public Book addAuthorToBookById(long bookId, long authorId) {
        Book book = getBookById(bookId);

        if (book == null)
            return null; // TODO add error

        // TODO catch error
        Author author = authorService.getAuthorById(authorId);
        
        if (author == null)
            return null; // TODO add error

        book.getAuthors().add(author);
        
        return bookRepository.save(book);
    }

    @Transactional
    public Book removeAuthorFromBookById(long bookId, long authorId) {
        Book book = getBookById(bookId);

        if (book == null)
            return null; // TODO add error

        // TODO catch error
        Author author = authorService.getAuthorById(authorId);
        
        if (author == null)
            return null; // TODO add error

        book.getAuthors().remove(author);

        return bookRepository.save(book);
    }

    @Transactional
    public Book addTagToBookTagList(long bookId, long tagId) {
        Book book = getBookById(bookId);

        if (book == null)
            return null; // TODO add error

        Tag tag = tagService.getTagById(tagId);

        if (tag == null) 
            return null; //TODO add error

        book.getTags().add(tag);

        return bookRepository.save(book);
    }

    @Transactional
    public Book removeTagFromBookTagList(long bookId, long tagId) {
        Book book = getBookById(bookId);

        if (book == null)
            return null; // TODO add error

        Tag tag = tagService.getTagById(tagId);

        if (tag == null) 
            return null; //TODO add error

        book.getTags().remove(tag);

        return bookRepository.save(book);
    }

}
