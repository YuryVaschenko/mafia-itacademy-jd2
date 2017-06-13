package by.itacademy.dao;

import by.itacademy.entity.Location;
import org.hibernate.Session;

/**
 * Created by Yury V. on 28.05.17.
 */

public class LocationDAO extends AbstractGenericDAO<Location> {

    public LocationDAO() {
    }

    public LocationDAO(Session session) {
        super(session);
    }

}
