package controller;

import dao.OrderDAO;
import persists.Category;
import persists.Order;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


@Named
@SessionScoped
public class OrderController implements Serializable {

    @Inject
    private OrderDAO orderDAO;

    private Order order;

    public List<Order> getAll() throws SQLException {
        return orderDAO.getAll();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public Order findById(int id) throws SQLException {
        return orderDAO.get(id);
    }

    public String editOrder(Order order) {
        this.order = order;
        return "/order.xhtml?faces-redirect=true";
    }


    public void deleteCategory(Order order) throws SQLException {
        orderDAO.delete(order);
    }

    public String saveCategory() throws SQLException {
        if(order.getId() == null) {
            orderDAO.save(order);
        } else {
            orderDAO.update(order);
        }
        return "/orders.xhtml?faces-redirect=true";
    }

    public String createCategory() {
        this.order = new Order();
        return "/order.xhtml?faces-redirect=true";
    }


}
