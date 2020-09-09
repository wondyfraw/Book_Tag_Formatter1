package mum.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.edu.domain.Book;
import mum.edu.domain.Category;
import mum.edu.domain.ISBNumber;

@Service
public class BookServiceImpl implements BookService {
    
	@Autowired
	CategoryService categoryService;
    /*
     * this implementation is not thread-safe
     */
//    private List<Category> categories;
    private List<Book> books;
    
    public BookServiceImpl() {
 
    	// Work around - - not calling categoryService...
    	// chicken & egg on startup
    	Category computingCategory = new Category(1, "Computing");

     	ISBNumber isbn = new ISBNumber(111,222,333);
        books = new ArrayList<Book>();
         books.add(new Book(1L, isbn, 
                "Servlet & JSP: A Tutorial", 
                computingCategory, "Budi Kurniawan"));
         books.add(new Book(2L, isbn,
                "C#: A Beginner's Tutorial",
                computingCategory, "Jayden Ky"));
    }

 
    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book save(Book book) {
        book.setId(getNextId());
        books.add(book);
        return book;
    }

    @Override
    public Book get(long id) {
        for (Book book : books) {
            if (id == book.getId()) {
                return book;
            }
        }
        return null;
    }
    
    @Override
    public Book update(Book book) {
        int bookCount = books.size();
        for (int i = 0; i < bookCount; i++) {
            Book savedBook = books.get(i);
            if (savedBook.getId() == book.getId()) {
                books.set(i, book);
                return book;
            }
        }
        return book;
    }
    
    @Override
    public long getNextId() {
        // needs to be locked
        long id = 0L;
        for (Book book : books) {
            long bookId = book.getId();
            if (bookId > id) {
                id = bookId;
            }
        }
        return id + 1;
    }
}
