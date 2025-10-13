package backend.bookstore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long categoryid;

    public String name;

    //yksi kategoria useaa kirjaa kohden 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kategoria")
    @JsonIgnore //tarvitaan, jotta vältytään loopilta json-tietoja palauttaessa
    private List<Book> books;

    public Category () {

    }

    public Category(String name) {
        this.name = name;
    }

    
    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {

        return "Category [id=" + categoryid + ", name=" + name + "]";
    }


    

    

    
}
