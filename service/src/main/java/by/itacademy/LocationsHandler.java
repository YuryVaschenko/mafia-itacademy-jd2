package by.itacademy;

import by.itacademy.dao.LocationDAO;
import by.itacademy.entity.Location;

/**
 * Created by Yury V. on 29.05.17.
 */
public class LocationsHandler {

    public Location findLocation(Long id){
        LocationDAO locationDAO = new LocationDAO();
        return locationDAO.findById(id);
    }

}
