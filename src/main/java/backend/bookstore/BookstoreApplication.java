package backend.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookstoreRepository;


@SpringBootApplication
public class BookstoreApplication {

private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstCommandLineRunner(BookstoreRepository repository) {
		return (args) -> {
			//luodaan kirjat ja tallennetaan kirjat h2-tietokantaan
			log.info("saving a few books");
			repository.save(new Book("Spindeln", "Lars Kepler", 2022, "978-91-0-016711-0", 15.95));
			repository.save(new Book("Kaninjägaren", "Lars Kepler", 2016, "978-91-0-013677-2", 13.95));
			repository.save(new Book("Harry Potter ja kirottu lapsi", "J.K. Rowling", 2016, "978-951-31-9276-1", 21.85));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			
			//oman repository-rajapinnan metodin testausta: 
			log.info("search all books where author is Lars Kepler");
			for (Book b : repository.findByAuthor("Lars Kepler")) {
				log.info(b.toString());
			}
		};
	}

}
