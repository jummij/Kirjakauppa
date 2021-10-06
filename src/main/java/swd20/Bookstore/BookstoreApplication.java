package swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.BookRepository;
import swd20.Bookstore.domain.Category;
import swd20.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	//testidatan luonti H2-databaseen sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner bookDemo(CategoryRepository catRepo, BookRepository bookRepo) {
		return (args) -> {
			
			
			log.info("saving book data");
		
			
			Category cat1 = new Category("Horror");
			Category cat2 = new Category("Romance");
			Category cat3 = new Category("Mystery");
			Category cat4 = new Category("Comedy");
			Category cat5 = new Category("Thriller");
			Category cat6 = new Category("Fantasy");
			Category cat7 = new Category("Comics");
			
			catRepo.save(cat1);
			catRepo.save(cat2);
			catRepo.save(cat3);
			catRepo.save(cat4);
			catRepo.save(cat5);
			catRepo.save(cat6);
			catRepo.save(cat7);
			
			log.info("fetch all categories");
			for ( Category category : catRepo.findAll()) {
				log.info(category.toString());
			}
			
			Book book1 = new Book("Ohana", "Dung Pham", 2018, "293048-12", 10.90, cat4);
			Book book2 = new Book("Tähtitaivas", "Jane Doe", 1989, "734579-02", 12.50, cat2);
			Book book3 = new Book("Loophole", "Chris Putty", 2011, "455434-54", 9.90, cat6);
			bookRepo.save(book1);
			bookRepo.save(book2);
			bookRepo.save(book3);
			
			
			
			
			
			log.info("fetch all books");
			for ( Book book : bookRepo.findAll()) {
				log.info(book.toString());
			}
			
			
			
			
		};
	}

}
