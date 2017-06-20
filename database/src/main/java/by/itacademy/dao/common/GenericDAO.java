package by.itacademy.dao.common;

import java.util.List;

/**
 * Created by Yury V. on 10.06.17.
 */

public interface GenericDAO<T extends DAOEntity> {

    Long saveNew(T entity);

    T findById(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);

}
