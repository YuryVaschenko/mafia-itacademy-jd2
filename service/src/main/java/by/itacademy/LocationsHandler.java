package by.itacademy;

import by.itacademy.dao.LocationDAOImpl;
import by.itacademy.entity.Location;

/**
 * Created by Yury V. on 29.05.17.
 */
public class LocationsHandler {

    public Location findLocation(Long id){
        LocationDAOImpl locationDAO = new LocationDAOImpl();
        return locationDAO.findById(id);
    }

}
