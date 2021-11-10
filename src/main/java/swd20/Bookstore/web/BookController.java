package swd20.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.BookRepository;
import swd20.Bookstore.domain.CategoryRepository;


@Controller
	public class BookController {
	
	/*@RequestMapping("/index") 
	public String index() {
		return "Tervetuloa";
	} */
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	CategoryRepository catRepo;
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookStoreList(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("books", bookRepo.findAll());
		return "booklist";
	}
	
	//REST service, get all books
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public @ResponseBody List<Book> booklistRest() {
		return (List<Book>) bookRepo.findAll();
	}
	
	//REST service, get book by ID
	@RequestMapping(value="/books/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id)
	{
		return bookRepo.findById(id);
	}
	
		
	@RequestMapping(value = "/add") // new book lomake
	public String addBook(Model model) {
			model.addAttribute("book", new Book());
			model.addAttribute("categories", catRepo.findAll());
			return "addbook";
	}
	
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addBook(Book book) {

		bookRepo.save(book);
		return "redirect:booklist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')") //vain ADMIN roolilla oikeus poistaa 
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {

		bookRepo.deleteById(id);
		return "redirect:/booklist";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookRepo.findById(id));
		return "editbook";
	}
	
}
