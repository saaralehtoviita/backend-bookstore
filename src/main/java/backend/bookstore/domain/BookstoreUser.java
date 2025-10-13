package backend.bookstore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="app_user")
public class BookstoreUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //hibernate osaa tutkia mikä on tietokannan id-generointitapa
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "app_password", nullable = false)
    private String passwordHashed;

    @Column(name = "app_role", nullable = false)
    private String role;

    //tyhjä konstruktori
    public BookstoreUser() {

    }

    public BookstoreUser(String username, String passwordHashed, String role) {
        this.username = username;
        this.passwordHashed = passwordHashed;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHashed() {
        return passwordHashed;
    }

    public void setPasswordHashed(String passwordHashed) {
        this.passwordHashed = passwordHashed;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

}
