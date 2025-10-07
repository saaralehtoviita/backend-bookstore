package backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import backend.bookstore.web.BookController;
import backend.bookstore.web.BookRestController;

@SpringBootTest
class BookstoreApplicationTests {

	//smoke-testi, testaa kriittisiä toimintoja
	//sovelluksen käynnistäyminen, jos käynnistyy oikein -> testi menee läpi
	@Test
	void contextLoads() {
	}


	@Autowired
	private BookController controller;

	
	@Test
	public void bookControllerLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Autowired 
	private BookRestController restController;

	@Test
	public void restControllerLoads() throws Exception {
		assertThat(restController).isNotNull();
	}

}
