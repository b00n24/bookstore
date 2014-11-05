package org.books.beans;

import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.books.application.exception.EmailAlreadyUsedException;
import org.books.persistence.Customer;
import org.books.services.CustomerService;

/**
 *
 * @author AWy
 */
@Named("customerBean")
@SessionScoped
public class CustomerBean implements Serializable {
    
    @Inject
    private CustomerService customerService;
    
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

    public void register() {
	try {
	    customerService.register(customer);
	} catch (EmailAlreadyUsedException ex) {
	    Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, "User exists already.", ex);
	}
    }
}
