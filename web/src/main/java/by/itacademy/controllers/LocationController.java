package by.itacademy.controllers;

import by.itacademy.entity.Location;
import by.itacademy.services.LocationService;
import org.hibernate.Hibernate;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Yury V. on 28.06.17.
 */

@Controller
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/")
    public String showLocationTestForm(Model model) {
        Location retrievedLocation = locationService.findLocation(1L);
        if (retrievedLocation == null) {
            Location location = new Location("27.525773", "53.89079");
            Long id = locationService.saveNewLocation(location);
            retrievedLocation = locationService.findLocation(id);
        }
        model.addAttribute("location", retrievedLocation);
        return "testpage";
    }

}
