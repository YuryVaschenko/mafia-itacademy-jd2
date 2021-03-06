package by.itacademy.dao.common;

import by.itacademy.config.TestDbConfig;
import by.itacademy.entity.BaseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Yury V. on 28.06.17.
 */

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestDbConfig.class)
public abstract class GenericDAOTest<T extends BaseEntity> {

    protected abstract GenericDAO<T> getDao();

    protected abstract T[] getModel();

    @Test
    public void saveNewAndFindByIdTest() {
        T model = getModel()[0];
        Long id = getDao().saveNew(model);
        T entity = getDao().findById(id);

        assertEquals(model, entity);
    }

    @Test
    public void updateTest() {
        T model = getModel()[0];
        Long id = getDao().saveNew(model);
        T modelToUpdate = getModel()[1];
        modelToUpdate.setId(id);
        getDao().update(modelToUpdate);
        T retrievedModel = getDao().findById(id);

        assertEquals(modelToUpdate, retrievedModel);
    }

    @Test
    public void findAllTest() {
        T[] model = getModel();
        for (T entity : model) {
            getDao().saveNew(entity);
        }
        List<T> retrievedList = getDao().findAll();

        assertArrayEquals(model, retrievedList.toArray());
    }

    @Test
    public void deleteTest() {
        T model = getModel()[0];
        Long id = getDao().saveNew(model);
        getDao().delete(model);

        getDao().findById(id);
        assertNull(getDao().findById(id));
    }
}
