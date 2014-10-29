/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.persistence;

/**
 *
 * @author AWy
 */
public class LineItem {

    private int quantity;
    private Book book;

    public LineItem(Book book) {
	this.book = book;
	this.quantity = 1;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public Book getBook() {
	return book;
    }

    public void setBook(Book book) {
	this.book = book;
    }

}
