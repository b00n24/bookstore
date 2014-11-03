package org.books.beans;

import java.io.Serializable;
import java.util.LinkedList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author AWy
 */
@Named("navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {

    private final LinkedList history = new LinkedList();
    private static final String PAGE_CATALOG_SEARCH = "catalogSearch";
    private static final String PAGE_BOOK_DETAILS = "bookDetails";
    private static final String PAGE_SHOPPING_CART = "shoppingCart";

    public String goToCatalogSearch() {
	saveCurrentPage();
	return PAGE_SHOPPING_CART;
    }

    public String goToBookDetails() {
	saveCurrentPage();
	return PAGE_BOOK_DETAILS;
    }

    public String goToShoppingCart() {
	saveCurrentPage();
	return PAGE_SHOPPING_CART;
    }

    public String goBack() {
	return history.isEmpty() ? PAGE_CATALOG_SEARCH : (String) history.pop();
    }

    private void saveCurrentPage() {
	String currentView = FacesContext.getCurrentInstance().getViewRoot().getViewId();
	String previousView = history.isEmpty() ? null : (String) history.peek();
	if (previousView == null || !previousView.equals(currentView)) {
	    history.push(currentView);
	}
    }
}
