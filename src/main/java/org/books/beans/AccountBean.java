package org.books.beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.books.application.Bookstore;
import org.books.application.exception.InvalidOrderStatusException;
import org.books.application.exception.OrderNotFoundException;
import org.books.persistence.Order;
import org.books.util.MessageFactory;

/**
 *
 * @author Silvan
 */
@Named("accountBean")
@SessionScoped
public class AccountBean implements Serializable {

    private static final String ORDER_CANCEL_ERROR = "org.books.orderCancelError";

    @Inject
    private Bookstore bookstore;

    @Inject
    private LoginBean loginBean;

    private Integer searchYear;
    private List<Order> searchResult;
    private Order selectedOrder;

    public Order getSelectedOrder() {
	return selectedOrder;
    }

    public List<Order> getSearchResult() {
	return searchResult;
    }

    public Integer getSearchYear() {
	return searchYear;
    }

    public void setSearchYear(Integer searchYear) {
	this.searchYear = searchYear;
    }

    public void searchOrders() {
	this.searchResult = bookstore.searchOrders(loginBean.getCustomer(), searchYear);
    }

    public void selectOrder(Order order) {
	this.selectedOrder = order;
    }

    public void cancelOrder(Order order) {
	try {
	    Order cancelOrder = bookstore.cancelOrder(order.getId());
	    int indexOf = searchResult.indexOf(order);
	    searchResult.remove(order);
	    searchResult.add(indexOf, cancelOrder);
	} catch (OrderNotFoundException | InvalidOrderStatusException ex) {
	    MessageFactory.error(ORDER_CANCEL_ERROR);
	}
    }
}
