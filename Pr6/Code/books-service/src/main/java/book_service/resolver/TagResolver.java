package book_service.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import book_service.model.Tag;
import book_service.model.input.tag.TagCreateInput;
import book_service.service.TagService;

@Controller
public class TagResolver {

    @Autowired
    TagService tagService;

    @QueryMapping
    public Tag getTag(@Argument long tagId) {
        return tagService.getTagById(tagId);
    }

    @QueryMapping
    public List<Tag> getAllTags() {
        return tagService.getAll();
    }

    @MutationMapping
    public Tag deleteTag(@Argument long tagId) {
        return tagService.deleteTagById(tagId);
    }

    @MutationMapping
    public Tag createTag(@Argument TagCreateInput input) {
        Tag tag = Tag.builder()
                     .name(input.getName())
                     .build();
        
        return tagService.saveTag(tag);
    }

}
