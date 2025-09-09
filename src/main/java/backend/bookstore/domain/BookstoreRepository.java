package backend.bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface BookstoreRepository extends CrudRepository<Book, Long> {

    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);

}
