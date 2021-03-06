package controller;

import dao.CategoryDAO;
import persists.Categories;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
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

    private Categories categories;

//    private List<Categories> lCategories;

//    public void preLoadData(ComponentSystemEvent componentSystemEvent) {
//        this.lCategories = categoryDAO.getAll();
//    }
//
////    public List<Categories> getAllCategories() {
////        return lCategories;
////    }

    public List<Categories> getAllCategories() {
        return categoryDAO.getAll();
    }


    public Categories getCategory() {
        return categories;
    }

    public void setCategory(Categories categories) {
        this.categories = categories;
    }

    public Categories findById(int id) throws SQLException {
        return categoryDAO.get(id);
    }

    public String editCategory(Categories categories) {
        this.categories = categories;
        return "/category.xhtml?faces-redirect=true";
    }


    public void deleteCategory(Categories categories) throws SQLException {
        categories.setStatus(false);
        categoryDAO.delete(categories);
    }

    public String saveCategory() throws SQLException {
        if(categories.getId() == null) {
            categoryDAO.save(categories);
        } else {
            categoryDAO.update(categories);
        }
        return "/categories.xhtml?faces-redirect=true";
    }

    public String createCategory() {
        this.categories = new Categories();
        return "/category.xhtml?faces-redirect=true";
    }


}
