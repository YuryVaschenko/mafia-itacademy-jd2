package by.itacademy.services;

import by.itacademy.entity.Location;

/**
 * Created by Yury V. on 28.06.17.
 */

public interface LocationService {

    Location findLocation(Long id);

    Long saveNewLocation(Location location);

    void deleteLocation(Location location);

}
