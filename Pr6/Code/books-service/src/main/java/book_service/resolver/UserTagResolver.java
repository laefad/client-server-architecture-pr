package book_service.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import book_service.model.TagList;
import book_service.model.input.tagList.TagListAddInput;
import book_service.model.input.tagList.TagListRemoveInput;
import book_service.service.TagListService;

@Controller
public class UserTagResolver {
    @Autowired
    TagListService tagListService;

    @MutationMapping
    public TagList addUserTagToBook(@Argument TagListAddInput input) {
        return tagListService.addTagToTagList(
            input.getUsername(),
            input.getBookId(),
            input.getTagId()
        );
    }

    @MutationMapping
    public TagList removeUserTagFromBook(@Argument TagListRemoveInput input) {
        return tagListService.removeTagFromTagList(
            input.getUsername(),
            input.getBookId(),
            input.getTagId()
        );
    }
}
