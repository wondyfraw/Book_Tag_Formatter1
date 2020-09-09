package mum.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mum.edu.domain.Book;
import mum.edu.domain.Category;
import mum.edu.service.BookService;
import mum.edu.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;

	// Used by all Controller handler methods
	// book list is added to model as "books"...
	@ModelAttribute("books")
	List<Book> addBookList(Model model) {
		System.out.println("===========addBookList=============");
		return bookService.getAllBooks();
	}

	@RequestMapping(value = { "/", "/book_list" })
	public String listBooks() {
		// List is added to model by @ModelAttribute on addBookList method
		
		return "BookList";
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
	public String inputBook(@ModelAttribute("newBook") Book book, Model model) {

		// Domain Object for search FORM - can also put it in Signature...
		// public String inputBook(@ModelAttribute("newBook") Book book,
		// @ModelAttribute("bookSearch") Book bookSearch,Model model) {
		model.addAttribute("bookSearch", new Book());

		// List is added to model by @ModelAttribute on addBookList method
		// displayed in BookAddForm

		List<Category> categories = categoryService.getAll();
		model.addAttribute("categories", categories);

		//Uncomment to test form:select with Map.
		/* Map<Integer,String> country = new HashMap<Integer,String>();
		 country.put(1, "United Stated");
		 country.put(2, "China");
		 country.put(3, "Singapore");
		 country.put(4, "Malaysia");
		 model.addAttribute("countryList", country);*/

		return "BookAddForm";
	}

	// from BookAddForm
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute("newBook") Book book, BindingResult result) {
		System.out.println(book);
		String[] suppressedFields = result.getSuppressedFields();
		if(suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		// Category id from form
		Category category = categoryService.getOne(book.getCategory().getId());
		book.setCategory(category);

		bookService.save(book);

		return "redirect:/book_list";
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
//		binder.setDisallowedFields("id");
		binder.setAllowedFields("isbn", "title", "category.id", "author", "publishDate");
	}


}