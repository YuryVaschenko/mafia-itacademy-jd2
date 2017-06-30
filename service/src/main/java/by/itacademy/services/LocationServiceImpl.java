package by.itacademy.services;

import by.itacademy.dao.LocationDAO;
import by.itacademy.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 29.05.17.
 */

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private final LocationDAO locationDAO;

    @Autowired
    public LocationServiceImpl(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    @Override
    public Location findLocation(Long id) {
        return locationDAO.findById(id);
    }

    @Override
    public Long saveNewLocation(Location location) {
        return locationDAO.saveNew(location);
    }

}
