package criteria;

import org.hibernate.annotations.Generated;
import persists.Customers;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(Customers.class)
public class Customers_ {
    public static volatile SingularAttribute<Customers, Integer> id;
    public static volatile SingularAttribute<Customers, String> name;
    public static volatile SingularAttribute<Customers, Boolean> status;
    public static volatile SingularAttribute<Customers, Integer> folio;
}
