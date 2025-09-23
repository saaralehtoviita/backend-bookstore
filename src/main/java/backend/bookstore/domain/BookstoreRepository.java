package backend.bookstore.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//tämä rajapinta hoitaa tietokannan CRUD-toiminnot
//käyttää CrudRepository rajapintaa
//toimii porttina tietokantaan, controller-luokka ei koske tietokantaan suoraan vaan pyytää repositorylta tiedot
//hibernate tallentaa crudrepositoryyn tietoja
//myöhemmin vaihdettu crudrepository -> jparepository koska laajempi
//kun jpa-repository, ei tarvita castaamista kontroller luokan metodeissa
public interface BookstoreRepository extends JpaRepository<Book, Long> {

    //omat metodit: 
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);

}
