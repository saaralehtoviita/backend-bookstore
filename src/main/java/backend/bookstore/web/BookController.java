package backend.bookstore.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import backend.bookstore.BookstoreApplication;
import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookstoreRepository;
import backend.bookstore.domain.CategoryRepository;
import backend.bookstore.domain.Category;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;



//vastaa selaimelta tuleviin HTTP-pyyntöihin
//palauttaa Thymeleafin avulla HTML-sivun tai datan
//käyttää BookstoreRepository-rajapointaa tietokantatoimintoihin
//tämä annotaatio vaaditaan, jotta spring boot tunnistaa:
@Controller
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    //määritellään käytössä oleva attribuutti:
    @Autowired
    private BookstoreRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    //injektoidaan konstruktori = otetaan siis bookrepository kontroller luokan käyttöön
/*     public BookController(BookstoreRepository repository) {
        this.repository = repository;
    } */

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
    @RequestMapping("/addBook")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    } 

    //kirjan poistaminen
    //kirjan poistaminen käynnistyy booklist.html-sivulta
    //delete-painike käynnistää 
    //urlista parametrinä id-arvo, jonka perusteella repositorysta (deleteById) haetaan poistettava kirja
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }

    //kirjan editoiminen
    //kirjaan editoimiseen päädytään booklist.html-sivulta
    //edit painike käynnistää 
    //urlista saadan parametrinä id-arvo, jolla tallennetaan book-oliolle tiedot (repositorysta findById metodilla etsitään)
    //tallennetaan olion tiedot modelille, ja välitetään thymeleafille book-muuttujana
    //endpointtina editbook -> editbook.html sivulle jossa lomake esitäytetyillä kirjan tiedoilla
    //save painike ohjaa save-metodiin (ei tarvita uutta, käytetään samaa kun addbookin kanssa)
    @RequestMapping("/editBookWithValidation/{id}") 
    public String editBook(@PathVariable Long id, Model model) {
        //Book book = repository.findById(id).orElse(null);
        model.addAttribute("book", repository.findById(id).orElse(null));
        model.addAttribute("categories", categoryRepository.findAll());
        return "editBookWithValidation";
    }

    //kirjan tallentaminen
    @PostMapping("/saveBook")
    public String save(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addBook";
        }
        repository.save(book);
        return "redirect:booklist";
    }

    //modelattribute - book pysyy mukana virheistä huolimatta
    //bindingresult ottaa validoinnin käyttöön - tutkii, rikkooko kirja sääntöjä
    @PostMapping("/saveEditedBook")
    public String saveEditedBook(@Valid @ModelAttribute("book") Book book,
     BindingResult bindingResult, Model model) {
        log.info("Check validation of " + book);
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "editBookWithValidation";
        }
        repository.save(book);    
        return "redirect:/booklist";
    }
    

    
}
