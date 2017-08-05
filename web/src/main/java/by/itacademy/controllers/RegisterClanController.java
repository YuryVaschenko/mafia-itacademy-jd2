package by.itacademy.controllers;

import by.itacademy.dto.RegisterNewClanInfoSample;
import by.itacademy.services.ClanService;
import by.itacademy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by Yury V. on 05.08.17.
 */

@Controller
public class RegisterClanController {

    private final ClanService clanService;
    private final UserService userService;

    @Autowired
    public RegisterClanController(UserService userService, ClanService clanService) {
        this.userService = userService;
        this.clanService = clanService;
    }


    //Register new clan page
    @GetMapping("/register")
    public String showRegisterClanPage(Model model) {
        model.addAttribute("regClanSample", new RegisterNewClanInfoSample());
        return "registerclan";
    }

    //Register new clan form data
    @PostMapping("/register")
    public String registerClan(@Valid @ModelAttribute("regClanSample") RegisterNewClanInfoSample regClanSample, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registerclan";
        }

        if (userService.isAccountUserExists(regClanSample.getLogin())) {
            model.addAttribute("existsLogin", "error.login_exists");
            return "registerclan";
        }

        if (clanService.isClanNameExists(regClanSample.getClanName())) {
            model.addAttribute("existsClanName", "error.clan_name_exists");
            return "registerclan";
        }

        clanService.registerNewClanBossAndAccountUser(regClanSample);
        return "redirect: /";
    }

}
