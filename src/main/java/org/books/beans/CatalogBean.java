/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.books.application.BookNotFoundException;
import org.books.application.Bookstore;
import org.books.persistence.Book;

/**
 *
 * @author Silvan
 */
@Named("catalogBean")
@SessionScoped
public class CatalogBean implements Serializable {

    @Inject
    private Bookstore bookstore;
    @Inject
    private ShoppingCartBean shoppingCart;
    @Inject
    private NavigationBean navigationBean;

    private String isbn;
    private Book book;
    private String message;

    // Uebung 3
    private String keywords;
    private List<Book> books;
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

    public String getMessage() {
	return message;
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
	this.message = null;
	this.book = null;
	this.selectedBook = null;
	this.books = null;
    }

//    Uebung 2
    public Object findBook() {
	Logger.getLogger(CatalogBean.class.getName()).log(Level.INFO, "> findBook: {0}", isbn);
	reset();
	try {
	    this.book = bookstore.findBook(isbn);
	} catch (BookNotFoundException ex) {
	    this.message = "No book found for ISBN: " + this.isbn;
	    this.book = null;
	    FacesMessage facesMessage = new FacesMessage(message);
	    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	    return null;
	}
	return navigationBean.goToBookDetails();
    }

    // Uebung 3
    public void searchBooks() {
	reset();
	this.books = bookstore.searchBooks(keywords);
	if (books.isEmpty()) {
	    message = "No matching books found";
	    FacesMessage facesMessage = new FacesMessage(message);
	    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
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
