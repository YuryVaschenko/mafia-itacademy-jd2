package by.itacademy;

import by.itacademy.dao.LocationDAO;
import by.itacademy.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Yury V. on 29.05.17.
 */

@Service
public class LocationsHandler {

    @Autowired
    private LocationDAO locationDAO;

    public Location findLocation(Long id) {
        return locationDAO.findById(id);
    }

}
