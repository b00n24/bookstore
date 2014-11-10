package org.books.beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.books.application.Bookstore;
import org.books.application.exception.PaymentFailedException;
import org.books.persistence.Address;
import org.books.persistence.CreditCard;
import org.books.persistence.Customer;
import org.books.persistence.LineItem;
import org.books.persistence.Order;
import org.books.util.MessageFactory;

/**
 *
 * @author Silvan
 */
@Named("orderBean")
@SessionScoped
public class OrderBean implements Serializable {
    private static final String PAYMENT_FAILED_ERROR = "org.books.paymentFailedError";

    @Inject
    private LoginBean loginBean;
    
    @Inject
    private NavigationBean navigationBean;
    
    @Inject
    private Bookstore bookstore;
    
    @Inject ShoppingCartBean shoppingCartBean;
    
    private Order order;

    public List<LineItem> getItems(){
	return shoppingCartBean.getItems();
    }
    
    public Order getOrder() {
	return order;
    }
     //FIXME: Customer aus Order herausholen
    public Customer getCustomer() {
	return loginBean.getCustomer();
    }

    public String getEmail() {
	return loginBean.getCustomer().getEmail();
    }

    //FIXME: Adresse aus Order herausholen
    public Address getAddress() {
	return loginBean.getCustomer().getAddress();
    }
    
    //FIXME: CreditCard aus Order herausholen
    public CreditCard getCreditCard(){
	return loginBean.getCustomer().getCreditCard();
    }
    
    public String creditCardExpireDate(){
	CreditCard card = getCreditCard();
	return String.format("%02d/%02d%n", card.getExpirationMonth(), card.getExpirationYear() % 1000);
    }
    
    public String send(){
	try {
	    this.order = bookstore.placeOrder(getCustomer(), getItems());
	} catch (PaymentFailedException ex) {
	    Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
	    MessageFactory.error(PAYMENT_FAILED_ERROR);
	    return null;
	}
	return navigationBean.goToOrderConfirmation();
    }
}
