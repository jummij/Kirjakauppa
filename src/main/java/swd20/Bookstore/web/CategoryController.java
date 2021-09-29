package swd20.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.Category;
import swd20.Bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

	
	@Autowired
	CategoryRepository catRepo;
	
	@RequestMapping(value = "/categorylist", method = RequestMethod.GET)
	public String categoryList(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", catRepo.findAll());
		return "categorylist";
	}
	
	@RequestMapping(value = "/addcat") // new category lomake
	public String addCategory(Model model) {
			model.addAttribute("category", new Category());
			return "addcategory";
	}
	
	@RequestMapping(value = "/savecat", method = RequestMethod.POST)
	public String addCategory(Category category) {

		catRepo.save(category);
		return "redirect:categorylist";
	
}
	
}
