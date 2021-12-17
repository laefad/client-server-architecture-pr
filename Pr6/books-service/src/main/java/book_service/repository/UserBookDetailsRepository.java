package book_service.repository;

import book_service.model.UserBookDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookDetailsRepository extends JpaRepository<UserBookDetails, Long> {
    Optional<UserBookDetails> findByUsername(String username);
}
