package by.itacademy.controllers;

import by.itacademy.dto.RegisterNewClanInfoSample;
import by.itacademy.services.ClanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @ModelAttribute("regClanSample")
    public RegisterNewClanInfoSample regClanSample() {
        return new RegisterNewClanInfoSample();
    }

    //Start page
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

    //Login page
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //Login with error page
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    //Register new clan page
    @GetMapping("/register")
    public String showRegisterClanPage() {
        return "registerclan";
    }

    //Register new clan form data
    @PostMapping("/register")
    public String registerClan(RegisterNewClanInfoSample regClanSample) {
        clanService.registerNewClanBossAndAccountUser(regClanSample);
        return "redirect: /";
    }

    //Redirect to page depending on the user role after authentication
    @RequestMapping("/redirect")
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
