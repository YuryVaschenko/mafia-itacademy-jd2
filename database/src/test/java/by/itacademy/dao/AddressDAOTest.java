package by.itacademy.dao;

import by.itacademy.entity.Address;
import by.itacademy.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Yury V. on 19.06.17.
 */
public class AddressDAOTest {

    @Test
    public void getAddressesByCityNameTest() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Address address = new Address();
        address.setCountry("Belarus");
        address.setCity("Minsk");
        AddressDAOImpl addressDAO = new AddressDAOImpl();
        addressDAO.saveNew(address);

        List<Address> retrievedAddresses = addressDAO.getAddressByCityName("Minsk");

        session.getTransaction().rollback();

        Assert.assertEquals(address, retrievedAddresses.get(0));
    }


}
