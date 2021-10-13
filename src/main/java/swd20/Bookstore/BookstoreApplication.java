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
import swd20.Bookstore.domain.User;
import swd20.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	//testidatan luonti H2-databaseen sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner bookDemo(CategoryRepository catRepo, BookRepository bookRepo, UserRepository urepository) {
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
			
			
			// Create users: admin/admin user/user, String username, String passwordHash, String role, String email
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user@gmail.com");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin@gmail.com");
			urepository.save(user1);
			urepository.save(user2);
			
			
			log.info("fetch all books");
			for ( Book book : bookRepo.findAll()) {
				log.info(book.toString());
			}
			
			
			
			
		};
	}

}
