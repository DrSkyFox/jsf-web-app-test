package service;

import javax.ejb.Local;
import java.util.List;
import java.util.concurrent.Future;

@Local
public interface ServiceLocal <T> {
    void insert(T t);
    void update(T t);
    void delete(T t);
    T findById(int id);
    List<T> findAll();
    Future<T> findByIdAsync(int id);

}
