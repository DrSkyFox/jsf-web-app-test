package dao;

import criteria.Customers_;
import persists.Customers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class CustomersDAO{


    @PersistenceContext(unitName = "DS")
    private EntityManager entityManager;



    @Transactional
    public List<Customers> getCustomers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customers> query = cb.createQuery(Customers.class);
        Root<Customers> c = query.from(Customers.class);
        ParameterExpression<Boolean> p = cb.parameter(Boolean.class);
        Predicate condition = cb.isTrue(c.get(Customers_.status));
        query.select(c).where(condition);
        TypedQuery<Customers> q = entityManager.createQuery(query);
        return q.getResultList();
    }

}
