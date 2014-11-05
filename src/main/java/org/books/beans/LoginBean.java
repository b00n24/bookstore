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
import javax.inject.Inject;
import javax.inject.Named;
import org.books.application.Bookstore;
import org.books.persistence.Book;
import org.books.util.MessageFactory;

/**
 *
 * @author Silvan
 */
@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private NavigationBean navigation;
    
    private String email;
    private String password;
    
    private boolean loggedIn;

    public boolean isLoggedIn() {
        return loggedIn;
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
        // TODO do implement
        boolean checkOk = true;
        if(checkOk){
            loggedIn = true;
        }else{
            loggedIn = false;
        }
        return navigation.goToNextPage();
    }
}
