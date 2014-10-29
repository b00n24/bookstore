/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

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
	items.add(new LineItem(book));
    }

}
