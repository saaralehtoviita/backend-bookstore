package backend.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookstoreRepository;


@Controller
public class BookController {

    private BookstoreRepository repository;

    //injektoidaan konstruktori = otetaan siis bookrepository kontroller luokan käyttöön
    public BookController(BookstoreRepository repository) {
        this.repository = repository;
    }

    @ResponseBody
    @GetMapping("/index") //http://localhost:8080/index 
    public String greeting() {
        return "Tervetuloa kirjakauppaan, sovellus näyttää toimivan!";
    }

    //listataan kaikki kirjat
    @RequestMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll()); //books välittyy templateen, pitää olla sama kun html-tiedostossa
        return "booklist";
    }

    //kirjan lisääminen
    @RequestMapping("/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        return "addbook";
    } 

    //kirjan tallentaminen
    @PostMapping("/save")
    public String save(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

    //kirjan poistaminen
    //kirjan poistaminen käynnistyy booklist.html-sivulta
    //delete-painike käynnistää 
    //urlista parametrinä id-arvo, jonka perusteella repositorysta (deleteById) haetaan poistettava kirja
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        repository.deleteById(id);
        return "redirect:../booklist";
    }

    //kirjan editoiminen
    //kirjaan editoimiseen päädytään booklist.html-sivulta
    //edit painike käynnistää 
    //urlista saadan parametrinä id-arvo, jolla tallennetaan book-oliolle tiedot (repositorysta findById metodilla etsitään)
    //tallennetaan olion tiedot modelille, ja välitetään thymeleafille book-muuttujana
    //endpointtina editbook -> editbook.html sivulle jossa lomake esitäytetyillä kirjan tiedoilla
    //save painike ohjaa save-metodiin (ei tarvita uutta, käytetään samaa kun addbookin kanssa)
    @RequestMapping("/edit/{id}") 
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = repository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "editbook";
    }

    
}
