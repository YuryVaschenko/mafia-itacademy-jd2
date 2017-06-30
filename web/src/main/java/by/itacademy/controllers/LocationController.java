package by.itacademy.controllers;

import by.itacademy.entity.Location;
import by.itacademy.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

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
    public String showLocationTestForm(Model model, HttpSession httpSession) {
        httpSession.setMaxInactiveInterval(60 * 60);
        Location retrievedLocation = locationService.findLocation(1L);
        if (retrievedLocation == null) {
            Location location = new Location();
            location.setLatitude("55.555");
            location.setLongitude("55.555");
            Long id = locationService.saveNewLocation(location);
            retrievedLocation = locationService.findLocation(id);
        }
        model.addAttribute("location", retrievedLocation);
        return "db_connection_test";
    }

}
