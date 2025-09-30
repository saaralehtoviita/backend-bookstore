package backend.bookstore.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.Category;
import backend.bookstore.domain.BookstoreRepository;
import backend.bookstore.domain.CategoryRepository;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BookRestController {

    private BookstoreRepository bRepository;
    private CategoryRepository cRepository;

    public BookRestController(BookstoreRepository bRepository, CategoryRepository cRepository) {
        this.bRepository = bRepository;
        this.cRepository = cRepository;
    }


    //kaikki kirjat JSON-formaatissa
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bRepository.findAll();
    }

    //kaikki kategoriat JSON-formaatissa
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return cRepository.findAll();
    }

    //kirjan etsintä id:n perusteella
    @GetMapping("/books/id/{id}")
    public Book getBookById(@PathVariable("id") Long bookId) {
        return bRepository.findById(bookId).orElse(null);    
    }

    //kirjan etsintä titlen perusteella
    //oma findbytitle-metodi palauttaa listan, minkä vuoksi striimataan ja etstiään listasta ensimmäinen haun titlen mukainen kirja
    //jos vaihdan metodin palauttamaan yhden kirjan (olettaen, että listassa vain yksi sen niminen kirja), niin metodi voisi suoraan paluttaa kirjan
    @GetMapping("/books/title/{title}")
    public Book getBookByTitle(@PathVariable("title") String title) {
        return bRepository.findByTitle(title)
            .stream()
            .findFirst()
            .orElse(null);
    }

    //kirjan etsintä authorin perusteella
    @GetMapping("/books/author/{author}")
    public List<Book> getBookByAuthor(@PathVariable("author") String author) {
        return bRepository.findByAuthor(author);
    }

    //kirjan lisääminen
    //testattu postmanin avulla; sovelluksen kautta lisätään siis JSON-formaatissa oleva olio books-enddpointille
    @PostMapping("books")
    public Book newBook(@RequestBody Book newBook) {
        return bRepository.save(newBook);
    }

    //kirjan editoiminen
    //localhost:8080/books/2 PUT komento editoi id:llä 2 löytyvän kirjaa
    @PutMapping("/books/{id}")
    public Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
        editedBook.setId(id);
        return bRepository.save(editedBook);
    }
/*         Book book = bRepository.findById(editedBook.getId()).orElse(null);
        if (book != null) {
            book.setTitle(editedBook.getTitle());
            book.setAuthor(editedBook.getAuthor());
            book.setIsbn(editedBook.getIsbn());
            book.setPublicationYear(editedBook.getPublicationYear());
            book.setKategoria(editedBook.getKategoria());
            return bRepository.save(book);
        }
        return null;
    } */


    //kirjan poistaminen id:n perusteella
    //localhost:8080/books/2 DELETE komento poistaa id:llä 2 löytyvän kirjan
    //palauttaa uuden bRepositoryn josta poistettu kyseinen kirja
    @DeleteMapping("/books/{id}")
    public List<Book> deleteBook(@PathVariable Long id) {
        bRepository.deleteById(id);
        return bRepository.findAll();
    }
    
}
