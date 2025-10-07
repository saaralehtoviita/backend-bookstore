package backend.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookstoreRepository;
import backend.bookstore.domain.BookstoreUser;
import backend.bookstore.domain.BookstoreUserRepository;
import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;

//tämä luokka on sovelluksen käynnistävä luokka (main-metodi)

//pakollinen annotaatio, jotta spring boot tunnistaa: 
@SpringBootApplication
public class BookstoreApplication {

private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	//alustaa tietokannan
	@Bean
	public CommandLineRunner bookstCommandLineRunner(BookstoreRepository bookRepository, CategoryRepository categoryRepository, BookstoreUserRepository userRepository) {
		return (args) -> {
			//luodaan kategorioita

			log.info("creating categories");
			Category thriller = categoryRepository.save(new Category("thriller"));
			Category fantasy = categoryRepository.save(new Category("fantasy"));
			Category music = categoryRepository.save(new Category("music"));
			Category children = categoryRepository.save(new Category("children"));
			Category technology = categoryRepository.save(new Category("technology"));



			//luodaan kirjat ja tallennetaan kirjat h2-tietokantaan
			log.info("saving a few books");
			bookRepository.save(new Book("Spindeln", "Lars Kepler", 2022, "978-91-0-016711-0", 15.95, thriller));
			bookRepository.save(new Book("Kaninjägaren", "Lars Kepler", 2016, "978-91-0-013677-2", 13.95, thriller));
			bookRepository.save(new Book("Harry Potter ja kirottu lapsi", "J.K. Rowling", 2016, "978-951-31-9276-1", 21.85, fantasy));
			bookRepository.save(new Book("Karhuherra Paddingtonin puutarha", "Michael Bond", 1973, "951-0-00699-8", 8.5, children));

			//luodaan käyttäjät ja tallenetaan tietokantaan
			log.info("saving users");
			BookstoreUser user1 = new BookstoreUser("user", "$2a$10$OigcyiZvjaOWJtoTe6WW6u3pNafFLCdxGMTEtuvvUyhg1AyHHNMRe", "USER");
			BookstoreUser user2 = new BookstoreUser("admin", "$2a$10$xEOX0/xeBWMLkkfqNACJnukTaXe3tdF2JacCLKjMXDtgzHBeF9X4i", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);


			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
			
			//oman repository-rajapinnan metodin testausta: 
			log.info("search all books where author is Lars Kepler");
			for (Book b : bookRepository.findByAuthor("Lars Kepler")) {
				log.info(b.toString());
			}

			log.info("fetching all users");
			for (BookstoreUser u: userRepository.findAll()) {
				log.info(u.getUsername());
			}
		};
	}

}
