package org.books.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.books.application.Bookstore;
import org.books.persistence.Book;
import org.books.util.MessageFactory;

/**
 *
 * @author Silvan
 */
@Named("catalogBean")
@SessionScoped
public class CatalogBean implements Serializable {

    private static final String INFO_NO_BOOK_FOUND = "org.books.infoNoBookFound";

    @Inject
    private Bookstore bookstore;
    @Inject
    private ShoppingCartBean shoppingCart;
    @Inject
    private NavigationBean navigationBean;

    private String isbn;
    private Book book;

    // Uebung 3
    private String keywords;
    private List<Book> books = new ArrayList<>();
    private Book selectedBook;

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    public String getIsbn() {
	return isbn;
    }

    public Book getBook() {
	return book;
    }

    public void setKeywords(String keywords) {
	this.keywords = keywords;
    }

    public String getKeywords() {
	return keywords;
    }

    public List<Book> getBooks() {
	return books;
    }

    public void setBooks(List<Book> books) {
	this.books = books;
    }

    public Book getSelectedBook() {
	return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
	this.selectedBook = selectedBook;
    }

    private void reset() {
	this.book = null;
	this.selectedBook = null;
	this.books.clear();
    }

    public void searchBooks() {
	reset();
	this.books = bookstore.searchBooks(keywords);
	if (books.isEmpty()) {
	    MessageFactory.info(INFO_NO_BOOK_FOUND);
	}
    }

    public String selectBook(Book book) {
	this.selectedBook = book;
	return navigationBean.goToBookDetails();
    }

    public void addToShoppingCart(Book book) {
	shoppingCart.add(book);
    }

}
