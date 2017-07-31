package by.itacademy.controllers;

import by.itacademy.dto.RegisterNewClanInfoSample;
import by.itacademy.services.ClanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static by.itacademy.entity.enums.Role.AUTHORITY;
import static by.itacademy.entity.enums.Role.CAPOREGIME;
import static by.itacademy.entity.enums.Role.SOLDIER;

/**
 * Created by Yury V. on 13.07.17.
 */

@Controller
public class StartController {

    private final Logger logger = Logger.getLogger(StartController.class);

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
            logger.error(e);
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
    public String registerClan(@Valid RegisterNewClanInfoSample regClanSample, BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (allErrors.isEmpty()) {
            clanService.registerNewClanBossAndAccountUser(regClanSample);
            return "redirect: /";
        }
        return "registerclan";
    }

    //Redirect to page depending on the user role after authentication
    @GetMapping("/redirect")
    public String redirectToAuthenticatedPage(HttpSession session) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Long clan_id = clanService.findClanIdByLogin(auth.getName());
        session.setAttribute("clan_id", clan_id);
        session.setMaxInactiveInterval(20*60);

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority(AUTHORITY.name()))) {
            return "redirect: /authority";
        }
        if (authorities.contains(new SimpleGrantedAuthority(CAPOREGIME.name()))) {
            return "redirect: /caporegime";
        }
        if (authorities.contains(new SimpleGrantedAuthority(SOLDIER.name()))) {
            return "redirect: /soldier";
        }
        return "redirect: /";
    }

}
