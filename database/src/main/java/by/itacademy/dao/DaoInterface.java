package by.itacademy.dao;

import java.io.Serializable;

/**
 * Created by Yury V. on 28.05.17.
 */
public interface DaoInterface<T> {

    Long saveNew(T t);

    T get(Long id);

    void update(T t);

    void delete(T t);

}
