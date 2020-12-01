package controller;


import dao.ProductDAO;

import persists.Products;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


@Named
@SessionScoped
public class ProductController implements Serializable {

    @Inject
    private ProductDAO productDAO;

    private Products products;

    public List<Products> getAll() throws SQLException {
        return productDAO.getAll();
    }

    public Products getProduct() {
        return products;
    }

    public void setProduct(Products products) {
        this.products = products;
    }

    public Products findById(int id) throws SQLException {
        return productDAO.get(id);
    }

    public String editProduct(Products products) {
        this.products = products;
        return "/product.xhtml?faces-redirect=true";
    }


    public void deleteProduct(Products products) throws SQLException {
        products.setStatus(false);
        productDAO.delete(products);
    }

    public String saveProduct() throws SQLException {
        if(products.getId() == null) {
            productDAO.save(products);
        } else {
            productDAO.update(products);
        }
        return "/products.xhtml?faces-redirect=true";
    }

    public String createProduct() {
        this.products = new Products();
        return "/product.xhtml?faces-redirect=true";
    }


}
