/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.books.persistence.Book;
import org.books.persistence.LineItem;

/**
 *
 * @author Silvan
 */
@Named("shoppingCartBean")
@SessionScoped
public class ShoppingCartBean implements Serializable {

    private List<LineItem> items = new ArrayList<>();

    public List<LineItem> getItems() {
	return items;
    }

    public void setItems(List<LineItem> items) {
	this.items = items;
    }

    public void add(Book book) {
	LineItem item = null;
	for (LineItem i : items) {
	    if (i.getBook().equals(book)) {
		item = i;
		break;
	    }
	}
	if (item != null) {
	    item.add();
	} else {
	    items.add(new LineItem(book));
	}
    }

    public void remove(LineItem item) {
	if (items.contains(item)) {
	    items.remove(item);
	}
    }

}
