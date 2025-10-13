package backend.bookstore.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;



//id-luodaan automaattisesti, eikä sitä siksi käytetä konstruktorissa (.AUTO osaa luoda id:n tietokannan vaatimusten perusteella)
//@Table(name="kirja") voidaan määritellä taulujen nimet tietokannan puolella
//luokkaan voidaan suoraan asettaa validointiarvoja
//luo olion tietokantaan, tämä annotaatio pakollinen:
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title cannot be empty.")
    @Size(min= 1, max= 250)
    private String title;

    @NotEmpty(message = "Author cannot be empty")
    @Size(min= 1, max= 250)
    private String author;

    @Min(value = 0, message = "Publishing year cannot be negative or null")
    @Column(name="publication_year")
    private int publicationYear;

    private String isbn;
    private double price;

    //monta kirjaa yhtä kategoriaa kohden 
    @ManyToOne
    @JoinColumn(name = "category_id") //categoryid tietokannassa oleva fk
    public Category kategoria;

    //konstruktori ilman parametrejä
    public Book() {
        
    }

    //konstruktori ilman kategoriaa
    public Book(String title, String author, int publicationYear, String isbn, double price) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
    }

    //konstruktori kategorian kanssa
    public Book(String title, String author, int publicationYear, String isbn, double price, Category kategoria) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
        this.kategoria = kategoria;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getKategoria() {
        return kategoria;
    }

    public void setKategoria(Category kategoria) {
        this.kategoria = kategoria;
    }

    @Override
    public String toString() {
        if (this.kategoria != null) 
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
                + ", isbn=" + isbn + ", price=" + price + ", category=" + kategoria + "]";
        else 
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
                + ", isbn=" + isbn + ", price=" + price + "]";
    }

}
