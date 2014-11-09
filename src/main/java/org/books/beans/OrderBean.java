package org.books.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.books.persistence.Address;
import org.books.persistence.CreditCard;
import org.books.persistence.Customer;

/**
 *
 * @author Silvan
 */
@Named("orderBean")
@SessionScoped
public class OrderBean implements Serializable {

    @Inject
    private LoginBean loginBean;

    public Customer getCustomer() {
	return loginBean.getCustomer();
    }

    public String getEmail() {
	return loginBean.getCustomer().getEmail();
    }

    public Address getAddress() {
	return loginBean.getCustomer().getAddress();
    }
    
    public CreditCard getCreditCard(){
	return loginBean.getCustomer().getCreditCard();
    }
    
    public String creditCardExpireDate(){
	CreditCard card = getCreditCard();
	return String.format("%02d/%02d%n", card.getExpirationMonth(), card.getExpirationYear() % 1000);
    }
}
