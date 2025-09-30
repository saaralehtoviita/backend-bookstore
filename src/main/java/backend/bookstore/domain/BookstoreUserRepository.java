package backend.bookstore.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookstoreUserRepository extends JpaRepository<BookstoreUser, Long> {
    BookstoreUser findByUsername(String username);
}
