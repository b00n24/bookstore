package org.books.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.books.application.exception.EmailAlreadyUsedException;
import org.books.persistence.Customer;
import org.books.services.CustomerService;
import org.books.util.MessageFactory;

/**
 *
 * @author AWy
 */
@Named("customerBean")
@SessionScoped
public class CustomerBean implements Serializable {
    
    private static final String WARNING_USER_EXISTS = "org.books.userExistsAlready";
    
    @Inject
    private CustomerService customerService;
    @Inject
    private NavigationBean navigationBean;
    
    private Customer customer;
    
    public CustomerBean() {
	customer = new Customer();
    }

    public Customer getCustomer() {
	return customer;
    }
    
    public void setCustomer(Customer customer) {
	this.customer = customer;
    }

    public String register() {
	Customer customer = null;
	try {
	    customer = customerService.register(customer);
	} catch (EmailAlreadyUsedException ex) {
	    MessageFactory.error(WARNING_USER_EXISTS);
	}
	if (customer != null) {
	    return navigationBean.goToNextPage();
	}
	
	return null;
    }
}
