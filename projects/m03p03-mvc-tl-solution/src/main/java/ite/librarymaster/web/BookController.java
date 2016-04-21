package ite.librarymaster.web;

import ite.librarymaster.model.Book;
import ite.librarymaster.service.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

/**
 * A Spring MVC @Controller controller handling requests for the
 * list of books page.   TODO: formular na vlozenie knihy(metoda controlera) + junit/view + ulozenie do DB(saveBook uz existuje)
 */
@Controller
public class BookController {
    final Logger logger = LoggerFactory.getLogger(BookController.class);

	
	private LibraryService libraryService;
	
	@Autowired
	public BookController(LibraryService libraryService) {
		super();
		this.libraryService = libraryService;
	}
	
	@RequestMapping("/books")
	public ModelAndView allBook() {
	    logger.info("allBook() <-");
	    ModelAndView modelAndView = new ModelAndView("book/books");
	    modelAndView.addObject("books", libraryService.getAllBooks());
	    return modelAndView;
	}
	
	@RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	public ModelAndView bookDetail(@PathVariable Long id) throws ItemNotFoundException{
	    logger.info("bookDetail() <-");
	    ModelAndView modelAndView = new ModelAndView("book/book");
	    modelAndView.addObject("book",libraryService.getBook(id));
	    return modelAndView;
	}
	@RequestMapping(value="/addbook", method=RequestMethod.GET)
	public ModelAndView addBook() {
		logger.info("addBook() <-");
	    ModelAndView modelAndView = new ModelAndView("book/addbook");
	    Book book = new Book();
	    modelAndView.addObject("book", book);
	    return modelAndView;		
	}
	
	@RequestMapping(value = "/processForm", method=RequestMethod.POST)
	public String processForm(Model model, @ModelAttribute Book book) {
	  logger.info("processForm() <-");
	  libraryService.saveBook(book);
	  return "result";
	}
	
}
