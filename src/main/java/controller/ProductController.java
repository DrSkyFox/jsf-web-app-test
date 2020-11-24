package controller;


import dao.ProductDAO;

import persists.Product;

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

    private Product product;

    public List<Product> getAll() throws SQLException {
        return productDAO.getAll();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product findById(int id) throws SQLException {
        return productDAO.get(id);
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }


    public void deleteProduct(Product product) throws SQLException {
        productDAO.delete(product);
    }

    public String saveProduct() throws SQLException {
        if(product.getId() == null) {
            productDAO.save(product);
        } else {
            productDAO.update(product);
        }
        return "/products.xhtml?faces-redirect=true";
    }

    public String createProduct() {
        this.product = new Product();
        return "/product.xhtml?faces-redirect=true";
    }


}
