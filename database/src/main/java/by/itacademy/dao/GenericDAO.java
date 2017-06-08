package by.itacademy.dao;

import java.util.List;

/**
 * Created by Yury V. on 28.05.17.
 */

public interface GenericDAO<T> {

    Long saveNew(T t);

    T findById(Long id);

    List<T> findAll();

    void update(T t);

    void delete(T t);

}
