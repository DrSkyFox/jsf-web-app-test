package controller;

import dao.CategoryDAO;
import persists.Category;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


@Named
@SessionScoped
public class CategoriesController implements Serializable {

    @Inject
    private CategoryDAO categoryDAO;

    private Category category;

    public List<Category> getAll() throws SQLException {
        return categoryDAO.getAll();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category findById(int id) throws SQLException {
        return categoryDAO.get(id);
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category.xhtml";
    }


    public void deleteCategory(Category category) throws SQLException {
        categoryDAO.delete(category);
    }

    public String saveCategory() throws SQLException {
        if(category.getiD() == null) {
            categoryDAO.save(category);
        } else {
            categoryDAO.update(category);
        }
        return "/categories.xhtml?faces-redirect=true";
    }

    public String createCategory() {
        this.category = new Category();
        return "/category.xhtml";
    }


}
