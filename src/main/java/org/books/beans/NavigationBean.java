package org.books.beans;

import java.io.Serializable;
import java.util.LinkedList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author AWy
 */
@Named("navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {

    @Inject
    LoginBean login;
    
    private final LinkedList<Pages> history = new LinkedList();
    private Pages nextView;

    public String goToCatalogSearch() {
	saveCurrentPage();
	return goToPage(Pages.PAGE_CATALOG_SEARCH);
    }

    public String goToBookDetails() {
	saveCurrentPage();
	return goToPage(Pages.PAGE_BOOK_DETAILS);
    }

    public String goToShoppingCart() {
	saveCurrentPage();
	return goToPage(Pages.PAGE_SHOPPING_CART);
    }

    public String goToRegistration() {
	saveCurrentPage();
	return goToPage(Pages.PAGE_REGISTRATION);
    }

    private String goToLogin() {
	return Pages.PAGE_LOGIN.getPageName();
    }

    String goToNextPage() {
	return nextView == null ? Pages.PAGE_CATALOG_SEARCH.getPageName() : nextView.getPageName();
    }

    public String goBack() {
	return history.isEmpty() ? Pages.PAGE_CATALOG_SEARCH.getPageName() : history.pop().getPageName();
    }

    private String goToPage(Pages page){
	if(page.isLoginNeeded() && !login.isLoggedIn()){
	    this.nextView = page;
	    return goToLogin();
    	}
	return page.getPageName();
    }
    private void saveCurrentPage() {
	Pages currentView = Pages.getPage(FacesContext.getCurrentInstance().getViewRoot().getViewId());
	Pages previousView = history.isEmpty() ? null : history.peek();
	if (previousView == null || !previousView.equals(currentView)) {
	    history.push(currentView);
	}
    }
    
    private enum Pages {

	PAGE_CATALOG_SEARCH("/catalogSearch.xhtml", false),
	PAGE_BOOK_DETAILS("/bookDetails.xhtml", true), 
	PAGE_SHOPPING_CART("/shoppingCart.xhtml", false), 
	PAGE_LOGIN("/login.xhtml", false), 
	PAGE_REGISTRATION("/registration.xhtml", false);
	
	private final String pageName;
	private final boolean loginNeeded;

	private Pages(String pageName, boolean loginNeeded) {
	    this.pageName = pageName;
	    this.loginNeeded = loginNeeded;
	}
	
	public static Pages getPage(String pageName){
	    for(Pages page : Pages.values()){
		if(page.getPageName().equals(pageName)){
		    return page;
		}
	    }
	    throw new IllegalArgumentException("PageName unknown: " + pageName);
	}
	
	public String getPageName() {
	    return pageName;
	}

	public boolean isLoginNeeded() {
	    return loginNeeded;
	}
    }    
}
