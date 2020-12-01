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
import javax.transaction.Transactional;
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
    @Transactional
    public Orders get(int id) throws SQLException {
        return entityManager.find(Orders.class, id);
    }

    @Override
    @Transactional
    public List<Orders> getAll() throws SQLException {
        return entityManager.createQuery("from Orders o where o.status = true").getResultList();
    }

    @Override
    @Transactional
    public void save(Orders orders) throws SQLException {
        orders.setStatus(true);
        entityManager.persist(orders);
    }

    @Override
    @Transactional
    public void update(Orders orders) throws SQLException {
        entityManager.merge(orders);
    }

    @Override
    @Transactional
    public void delete(Orders orders) throws SQLException {
        entityManager.merge(orders);
    }
}
