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

public class AddressTest {


    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void addressSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Address address = new Address();
        address.setCountry("Belarus");
        address.setCity("Minsk");
        address.setStreet("Kolasa");
        address.setHouse("55a");
        Long id = (Long) session.save(address);
        Address retrievedAddress = session.load(Address.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(address, retrievedAddress);
    }

    @Test
    public void locationThroughAddressSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Address address = new Address();
        Location location = new Location();
        location.setLatitude("77.7777");
        location.setLongitude("55.5555");
        address.setLocation(location);
        location.setAddress(address);
        Long id = (Long) session.save(address);
        Address retrievedAddress = session.load(Address.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(address.getLocation(), retrievedAddress.getLocation());
    }

    @Test
    public void cascadeDeletingAddressAndLocationTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Address address = new Address();
        address.setCountry("Belarus");
        Location location = new Location();
        location.setLongitude("55.555");
        location.setLatitude("55.555");
        address.setLocation(location);
        location.setAddress(address);
        Long id = (Long) session.save(address);
        session.delete(address);

        Address retrievedAddress = session.get(Address.class, id);
        Location retrievedLocation = session.get(Location.class, 1L);
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
