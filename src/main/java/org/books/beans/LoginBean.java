/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.books.application.exception.InvalidCredentialsException;
import org.books.persistence.Customer;
import org.books.services.CustomerService;
import org.books.util.MessageFactory;

/**
 *
 * @author Silvan
 */
@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    private static final String ERROR_INVALID_CREDENTIALS = "org.books.errorInvalidCredentials";

    @Inject
    private NavigationBean navigation;
    
    @Inject
    private CustomerService customerService;
    
    private String email;
    private String password;
    
    private Customer customer;

    public boolean isLoggedIn() {
        return customer != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
	try {
	    customer = customerService.authenticate(email, password);
	} catch (InvalidCredentialsException ex) {
	    MessageFactory.error(ERROR_INVALID_CREDENTIALS);
	    return null;
	}
        return navigation.goToNextPage();
    }
}
