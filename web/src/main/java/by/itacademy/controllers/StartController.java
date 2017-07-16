package by.itacademy.controllers;

import by.itacademy.services.ClanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

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

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterClanPage() {
        return "registerclan";
    }

    @GetMapping("/redirect")
    public String redirectToAuthenticatedPage(Principal principal) {
        switch (principal.getName().toUpperCase()) {
            case "AUTHORITY":
                return "redirect: /authority";
            case "CAPOREGIME":
                return "redirect: /caporegime";
            case "SOLDIER":
                return "redirect: /soldier";
            default:
                return "redirect: /";
        }
    }

}
