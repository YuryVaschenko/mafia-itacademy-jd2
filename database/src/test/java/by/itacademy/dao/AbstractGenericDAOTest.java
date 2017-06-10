package by.itacademy.dao;

import by.itacademy.entity.Address;
import by.itacademy.entity.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yury V. on 10.06.17.
 */
public class AbstractGenericDAOTest {

    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void saveNewTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Location location = new Location();
        location.setLatitude("55.55");
        location.setLongitude("55.55");
        LocationDAO locationDAO = new LocationDAO();
        Long id_location = locationDAO.saveNew(location);

        Location secondLocation = new Location();
        secondLocation.setLatitude("77.77");
        secondLocation.setLongitude("77.77");
        Long id_secondLocation = locationDAO.saveNew(secondLocation);

        Address address = new Address();
        address.setCountry("Belarus");
        AddressDAO addressDAO = new AddressDAO();
        Long id_address = addressDAO.saveNew(address);

        Location retrievedLocation = session.get(Location.class, id_location);
        Location retrievedSecondLocation = session.get(Location.class, id_secondLocation);
        Address retrievedAddress = session.get(Address.class, id_address);

        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(location, retrievedLocation);
        Assert.assertEquals(secondLocation, retrievedSecondLocation);
        Assert.assertEquals(address, retrievedAddress);
    }

    @Test
    public void findByIdTest() {
        Session session = SESSION_FACTORY.openSession();
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

        //session.flush();
        session.getTransaction().commit();
        session.close();
        LocationDAO locationDAO = new LocationDAO();
        AddressDAO addressDAO = new AddressDAO();
        Location retrievedLocation = locationDAO.findById(location_id);
        Location retrievedSecondLocation = locationDAO.findById(secondLocation_id);
        Address retrievedAddress = addressDAO.findById(address_id);


        Assert.assertEquals(location, retrievedLocation);
        Assert.assertEquals(secondLocation, retrievedSecondLocation);
        Assert.assertEquals(address, retrievedAddress);
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }

}
