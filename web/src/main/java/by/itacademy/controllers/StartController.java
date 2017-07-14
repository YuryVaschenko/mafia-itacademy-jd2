package by.itacademy.controllers;

import by.itacademy.entity.Clan;
import by.itacademy.entity.Location;
import by.itacademy.services.ClanService;
import by.itacademy.services.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Yury V. on 13.07.17.
 */

@Controller
public class StartController {

    private final ClanService clanService;
    private final LocationService locationService;

    @Autowired
    public StartController(ClanService clanService, LocationService locationService) {
        this.clanService = clanService;
        this.locationService = locationService;
    }

    @GetMapping("/")
    public String showStartPage(Model model) {

        ObjectMapper mapper = new ObjectMapper();
        String jsonClans = "";

        try {
            jsonClans = mapper.writeValueAsString(clanService.getAllClans());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("allClans", jsonClans);

        return "index";
    }

}
