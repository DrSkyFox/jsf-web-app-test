package controller;

import dao.OrderDAO;
import persists.Orders;

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

    private Orders orders;

    public List<Orders> getAll() throws SQLException {
        return orderDAO.getAll();
    }

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders orders) {
        this.orders = orders;
    }


    public Orders findById(int id) throws SQLException {
        return orderDAO.get(id);
    }

    public String editOrder(Orders orders) {
        this.orders = orders;
        return "/order.xhtml?faces-redirect=true";
    }


    public void deleteOrder(Orders orders) throws SQLException {
        orderDAO.delete(orders);
    }

    public String saveOrder() throws SQLException {
        if(orders.getId() == null) {
            orderDAO.save(orders);
        } else {
            orderDAO.update(orders);
        }
        return "/orders.xhtml?faces-redirect=true";
    }

    public String createOrder() {
        this.orders = new Orders();
        return "/order.xhtml?faces-redirect=true";
    }


}
