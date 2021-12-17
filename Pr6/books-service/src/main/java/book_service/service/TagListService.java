package book_service.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book_service.model.Book;
import book_service.model.Tag;
import book_service.model.TagList;
import book_service.model.TagListId;
import book_service.model.UserBookDetails;
import book_service.repository.TagListRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagListService {
    
    @Autowired
    TagListRepository tagListRepository;

    @Autowired
    TagService tagService;

    @Autowired
    BookService bookService;

    @Autowired
    UserBookDetailsService userBookDetailsService;

    @Transactional(readOnly = true)
    public TagList getTagListById(TagListId tagListId) {
        return tagListRepository.getById(tagListId);
    }

    @Transactional
    public TagList createTagList(String username, long bookId) {
        Book book = bookService.getBookById(bookId);

        if (book == null)
            return null; // TODO add error

        UserBookDetails userBookDetails = userBookDetailsService.getOrCreateByUsername(username);

        TagListId tagListId = createTagListId(username, bookId);
        TagList tagList = getTagListById(tagListId);

        if (tagList != null)
            return null; // TODO add error

        tagList = TagList.builder()
                         .tagListId(tagListId)
                         .user(userBookDetails)
                         .book(book)
                         .tags(new HashSet<>())
                         .build();

        return tagListRepository.save(tagList);
    }

    @Transactional
    public TagList getOrCreateTagList(String username, long bookId) {
        Book book = bookService.getBookById(bookId);

        if (book == null)
            return null; // TODO add error

        UserBookDetails userBookDetails = userBookDetailsService.getOrCreateByUsername(username);

        TagListId tagListId = createTagListId(username, bookId);
        TagList tagList = getTagListById(tagListId);

        if (tagList != null)
            return tagList;

        tagList = TagList.builder()
                         .tagListId(tagListId)
                         .user(userBookDetails)
                         .book(book)
                         .tags(new HashSet<>())
                         .build();

        return tagListRepository.save(tagList);
    }

    @Transactional
    public TagList addTagToTagList(String username, long bookId, long tagId) {
        TagList tagList = getOrCreateTagList(username, bookId);

        Tag tag = tagService.getTagById(tagId);

        if (tag == null) 
            return null; //TODO add error

        tagList.getTags().add(tag);

        return tagListRepository.save(tagList);
    }

    @Transactional
    public TagList removeTagFromTagList(String username, long bookId, long tagId) {
        TagList tagList = getTagListById(createTagListId(username, bookId));

        if (tagList == null)
            return null; //TODO add error
        
        Tag tag = tagService.getTagById(tagId);

        if (tag == null) 
            return null; //TODO add error

        tagList.getTags().remove(tag);

        return tagListRepository.save(tagList);
    }

    @Transactional
    public TagList removeTagList(String username, long bookId) {
        TagList tagList = getTagListById(createTagListId(username, bookId));

        if (tagList != null)
            return null; //TODO add error

        tagListRepository.delete(tagList);
        return tagList;
    }

    public TagListId createTagListId(String username, long bookId) {
        return TagListId.builder()
                        .bookId(bookId)
                        .username(username)
                        .build();
    }
}
