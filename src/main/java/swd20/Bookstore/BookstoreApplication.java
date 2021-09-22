package swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	//testidatan luonti H2-databaseen sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepo) {
		return (args) -> {
			log.info("saving book data");
			Book book1 = new Book("Ohana", "Dung Pham", 2018, "293048-12", 10.90);
			Book book2 = new Book ("Tähtitaivas", "Jane Doe", 1989, "734579-02", 12.50);
			bookRepo.save(book1);
			bookRepo.save(book2);
			
			log.info("fetch all books");
			for ( Book book : bookRepo.findAll()) {
				log.info(book.toString());
			}
			
		};
	}

}
