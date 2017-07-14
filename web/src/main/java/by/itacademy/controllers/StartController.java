package by.itacademy.controllers;

import by.itacademy.entity.Clan;
import by.itacademy.entity.Location;
import by.itacademy.services.ClanService;
import by.itacademy.services.LocationService;
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

    @Autowired
    public StartController(ClanService clanService) {
        this.clanService = clanService;
    }

    @GetMapping("/")
    public String showStartPage(Model model) {

        model.addAttribute("allClans", clanService.getAllClans());

        return "index";
    }

}
