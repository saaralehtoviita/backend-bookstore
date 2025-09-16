package backend.bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

//tämä rajapinta hoitaa tietokannan CRUD-toiminnot
//käyttää CrudRepository rajapintaa
//toimii porttina tietokantaan, controller-luokka ei koske tietokantaan suoraan vaan pyytää repositorylta tiedot
//hibernate tallentaa crudrepositoryyn tietoja
public interface BookstoreRepository extends CrudRepository<Book, Long> {

    //omat metodit: 
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);

}
