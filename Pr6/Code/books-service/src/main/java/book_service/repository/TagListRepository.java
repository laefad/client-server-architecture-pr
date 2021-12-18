package book_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book_service.model.TagList;
import book_service.model.TagListId;

@Repository
public interface TagListRepository extends JpaRepository<TagList, TagListId> {
    
}
