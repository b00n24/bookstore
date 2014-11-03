/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private Map<String, LineItem> itemsMap = new HashMap();

    public List<LineItem> getItems() {
	return new ArrayList<>(itemsMap.values());
    }

    public void add(Book book) {
	LineItem item = itemsMap.get(book.getIsbn());
	if (item != null) {
	    item.add();
	} else {
	    itemsMap.put(book.getIsbn(), new LineItem(book));
	}
    }

    public void remove(LineItem item) {
	if(itemsMap.containsKey(item.getBook().getIsbn())){
	    itemsMap.remove(item.getBook().getIsbn());
	}
    }
}
