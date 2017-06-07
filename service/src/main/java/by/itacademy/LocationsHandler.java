package by.itacademy;

import by.itacademy.dao.DaoImplementation;
import by.itacademy.entity.Location;

/**
 * Created by Yury V. on 29.05.17.
 */
public class LocationsHandler {

    public Long addNewLocationInDatabase(String name, String coords){
        DaoImplementation daoImplementation = new DaoImplementation();
        Location location = new Location();
        location.setName(name);
        location.setCoords(coords);
        return daoImplementation.saveNew(location);
    }

    public Location findLocation(Long id){
        DaoImplementation daoImplementation = new DaoImplementation();
        return daoImplementation.findById(id);
    }
    public Location findLocation(String name){
        DaoImplementation daoImplementation = new DaoImplementation();
        return daoImplementation.findByName(name);
    }

}
