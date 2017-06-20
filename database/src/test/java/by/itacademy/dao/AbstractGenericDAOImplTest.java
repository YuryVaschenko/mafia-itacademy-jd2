package by.itacademy.dao;

import by.itacademy.entity.Address;
import by.itacademy.entity.Location;
import by.itacademy.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yury V. on 10.06.17.
 */

public class AbstractGenericDAOImplTest {

    @Test
    public void saveNewTest() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Location location = new Location();
        location.setLatitude("55.55");
        location.setLongitude("55.55");
        LocationDAOImpl locationDAO = new LocationDAOImpl();
        Long id_location = locationDAO.saveNew(location);

        Location secondLocation = new Location();
        secondLocation.setLatitude("77.77");
        secondLocation.setLongitude("77.77");
        Long id_secondLocation = locationDAO.saveNew(secondLocation);

        Address address = new Address();
        address.setCountry("Belarus");
        AddressDAOImpl addressDAO = new AddressDAOImpl();
        Long id_address = addressDAO.saveNew(address);

        Location retrievedLocation = session.get(Location.class, id_location);
        Location retrievedSecondLocation = session.get(Location.class, id_secondLocation);
        Address retrievedAddress = session.get(Address.class, id_address);


        Assert.assertEquals(location, retrievedLocation);
        Assert.assertEquals(secondLocation, retrievedSecondLocation);
        Assert.assertEquals(address, retrievedAddress);

        session.getTransaction().rollback();
    }

    @Test
    public void findByIdTest() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Location location = new Location();
        location.setLatitude("55.55");
        location.setLongitude("55.55");
        Long location_id = (Long) session.save(location);

        Location secondLocation = new Location();
        secondLocation.setLatitude("77.77");
        secondLocation.setLongitude("77.77");
        Long secondLocation_id = (Long) session.save(secondLocation);

        Address address = new Address();
        address.setCountry("Belarus");
        Long address_id = (Long) session.save(address);

        LocationDAOImpl locationDAO = new LocationDAOImpl();
        Location retrievedLocation = locationDAO.findById(location_id);
        Location retrievedSecondLocation = locationDAO.findById(secondLocation_id);
        AddressDAOImpl addressDAO = new AddressDAOImpl();
        Address retrievedAddress = addressDAO.findById(address_id);

        Assert.assertEquals(location, retrievedLocation);
        Assert.assertEquals(secondLocation, retrievedSecondLocation);
        Assert.assertEquals(address, retrievedAddress);

        session.getTransaction().rollback();
    }

}
