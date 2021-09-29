package swd20.Bookstore.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.BookRepository;

@Controller
	public class BookController {
	
	/*@RequestMapping("/index") 
	public String index() {
		return "Tervetuloa";
	} */
	
	@Autowired
	BookRepository bookRepo;
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookStoreList(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("books", bookRepo.findAll());
		return "booklist";
	}
		
	@RequestMapping(value = "/add") // new student lomake
	public String addBook(Model model) {
			model.addAttribute("book", new Book());
			return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addBook(Book book) {

		bookRepo.save(book);
		return "redirect:booklist";
	}
	
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
