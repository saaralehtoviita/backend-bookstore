package backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.Category;
import backend.bookstore.domain.BookstoreRepository;
import backend.bookstore.domain.CategoryRepository;


//annotaatio ajonaikaisen tietokannan (h2) käytön testaamista varten 
@DataJpaTest
public class BookstoreRepositoryTestsJPA {

    //injektoidaan kirjarepositorio
    @Autowired
    private BookstoreRepository bRepository;

    //otsikolla etsiminen - palauttaako oikean kirjailijan
    @Test
    public void findByTitleShouldReturnAuthor() {
        List<Book> books = bRepository.findByTitle("Spindeln");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Lars Kepler");       
    }

    //kirjailijalla etsiminen - palauttaako oikeat kirjat
    @Test
    public void findByAuthorShouldReturnBooks() {
        List<Book> books = bRepository.findByAuthor("Lars Kepler");
        assertThat(books).hasSize(2);
        assertThat(books.get(0).getTitle()).isEqualTo("Spindeln");
        assertThat(books.get(1).getTitle()).isEqualTo("Kaninjägaren");
    }

    //injektoidaan kategoriarepositorio
    @Autowired
    private CategoryRepository cRepository;

    //uuden kirjan luominen kategoriatiedolla
    @Test
    public void createNewBook() {
        Category c = new Category("testikategoria");
        cRepository.save(c);
        Long idCat = c.getCategoryid();
        assertThat(cRepository.findById(idCat).isPresent());

        Book b = new Book("Testkikirja", "Test Author", 2020, "111-222-333", 25.50, c);
        bRepository.save(b);
        Long idBook = b.getId();
        assertThat(bRepository.findById(idBook).isPresent());
        //assertThat(b.getId()).isNotNull();
    }

    @Test
    public void deleteBook() {
        Book book = new Book("Testikirja poistamiseen", "Tester", 2025, "1234-1234", 15.00);
        bRepository.save(book);
        Long testId = book.getId();
        assertThat(bRepository.findById(testId).isPresent());
        bRepository.deleteById(testId);
        assertThat(bRepository.findById(testId)).isNotPresent();
    }



}
