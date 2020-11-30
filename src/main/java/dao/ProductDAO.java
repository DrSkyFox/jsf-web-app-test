package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persists.Products;

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
public class ProductDAO implements Dao<Products> {

    Logger logger = LoggerFactory.getLogger(ProductDAO.class);



    @PersistenceContext(unitName = "DS")
    private EntityManager entityManager;


    @Override
    public Products get(int id) throws SQLException {
        return entityManager.find(Products.class, id);
    }

    @Override
    public List<Products> getAll() throws SQLException {
        return entityManager.createQuery("from Products", Products.class).getResultList();
    }

    @Override
    public void save(Products products) throws SQLException {
        entityManager.persist(products);
    }

    @Override
    public void update(Products products) throws SQLException {
        entityManager.merge(products);
    }

    @Override
    public void delete(Products products) throws SQLException {
        entityManager.remove(products);
    }
}
