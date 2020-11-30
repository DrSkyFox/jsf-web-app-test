package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persists.Orders;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Named
@ApplicationScoped
public class OrderDAO implements Dao<Orders> {


    Logger logger  = LoggerFactory.getLogger(OrderDAO.class);


    @PersistenceContext(unitName = "DS")
    private EntityManager entityManager;

    @Override
    public Orders get(int id) throws SQLException {
        return entityManager.find(Orders.class, id);
    }

    @Override
    public List<Orders> getAll() throws SQLException {
        return entityManager.createQuery("from Orders o").getResultList();
    }

    @Override
    public void save(Orders orders) throws SQLException {
        entityManager.persist(orders);
    }

    @Override
    public void update(Orders orders) throws SQLException {
        entityManager.merge(orders);
    }

    @Override
    public void delete(Orders orders) throws SQLException {
        entityManager.remove(orders);
    }
}
