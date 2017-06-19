package by.itacademy.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yury V. on 08.06.17.
 */

public class LocationTest {


    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void locationSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Location location = new Location();
        location.setLatitude("55.55");
        location.setLongitude("55.55");
        Long id = (Long) session.save(location);
        Location retrievedLocation = session.load(Location.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(location, retrievedLocation);
    }

    @Test
    public void AddressThroughLocationSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Location location = new Location();
        location.setLatitude("77.7777");
        location.setLongitude("55.5555");
        Address address = new Address();
        location.setAddress(address);
        address.setLocation(location);
        Long id = (Long) session.save(location);
        Location retrievedLocation = session.load(Location.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(location.getAddress(), retrievedLocation.getAddress());
    }

    @Test
    public void cascadeDeletingLocationAndAddressTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Location location = new Location();
        location.setLongitude("55.555");
        location.setLatitude("55.555");
        Address address = new Address();
        address.setCountry("Belarus");
        location.setAddress(address);
        address.setLocation(location);
        Long id = (Long) session.save(location);
        session.delete(location);

        Location retrievedLocation = session.get(Location.class, id);
        Address retrievedAddress = session.get(Address.class, 1L);
        session.getTransaction().commit();
        session.close();

        Assert.assertNull(retrievedAddress);
        Assert.assertNull(retrievedLocation);
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }

}
