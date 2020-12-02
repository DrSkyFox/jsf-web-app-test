package dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persists.Categories;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

@Named
@ApplicationScoped
public class CategoryDAO implements Dao<Categories>{

    Logger logger  = LoggerFactory.getLogger(CategoryDAO.class);

    @PersistenceContext(unitName = "DS")
    private EntityManager entityManager;



    @Override
    @Transactional
    public Categories get(int id) {
        return entityManager.find(Categories.class, id);
    }

    @Override
    @Transactional
    public List<Categories> getAll() {
        return entityManager.createQuery("from Categories c where c.status=true", Categories.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void save(Categories categories){
        categories.setStatus(true);
        entityManager.persist(categories);
    }

    @Override
    @Transactional
    public void update(Categories categories) {
        entityManager.merge(categories);
    }

    @Override
    @Transactional
    public void delete(Categories categories) {
        entityManager.merge(categories);
    }


}
