package by.itacademy.controllers;

import by.itacademy.dto.RegisterNewAuthorityInfoSample;
import by.itacademy.dto.RegisterNewCaporegimeInfoSample;
import by.itacademy.dto.RegisterNewSoldierInfoSample;
import by.itacademy.entity.Authority;
import by.itacademy.entity.enums.Specialization;
import by.itacademy.services.MemberService;
import by.itacademy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yury V. on 15.07.17.
 */

@Controller
@RequestMapping("/authority")
public class AuthorityController {

    private final MemberService memberService;
    private final UserService userService;

    @Autowired
    public AuthorityController(MemberService memberService, UserService userService) {
        this.memberService = memberService;
        this.userService = userService;
    }

    @ModelAttribute("allSpecTypes")
    public List<Specialization> allSpecializationTypes() {
        return Arrays.asList(Specialization.values());
    }

    @GetMapping
    public String showStartAuthorityPage() {
        return "redirect: /authority/profile";
    }

    @GetMapping("/groups")
    public String showGroupsPage() {
        return "/authority/groups";
    }

    @GetMapping("/affairs")
    public String showAffairsPage() {
        return "affairs";
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authority authority = (Authority) memberService.findMemberByLogin(user.getUsername());
        model.addAttribute("authority", authority);
        return "profile";
    }

    @GetMapping("/members")
    public String showMembersPage() {
        return "/authority/members";
    }

    @GetMapping("/members/add-authority")
    public String showAddAuthorityPage(Model model) {
        model.addAttribute("authority", new RegisterNewAuthorityInfoSample());
        return "/authority/add_authority";
    }

    @PostMapping("/members/add-authority")
    public String registerAuthority(@Valid @ModelAttribute("authority") RegisterNewAuthorityInfoSample authorityInfoSample, BindingResult bindingResult, Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "/authority/add_authority";
        }
        if (userService.isAccountUserExists(authorityInfoSample.getLogin())) {
            model.addAttribute("existsLogin", "error.login_exists");
            return "/authority/add_authority";
        }
        memberService.saveNewAuthority((Long) session.getAttribute("clan_id"), authorityInfoSample);

        return "redirect:/authority/members";
    }

    @GetMapping("/members/add-caporegime")
    public String showAddCaporegimePage(Model model) {
        model.addAttribute("caporegime", new RegisterNewCaporegimeInfoSample());
        return "/authority/add_caporegime";
    }

    @PostMapping("/members/add-caporegime")
    public String registerCaporegime(@Valid @ModelAttribute("caporegime") RegisterNewCaporegimeInfoSample caporegimeInfoSample, BindingResult bindingResult, Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "/authority/add_caporegime";
        }
        if (userService.isAccountUserExists(caporegimeInfoSample.getLogin())) {
            model.addAttribute("existsLogin", "error.login_exists");
            return "/authority/add_caporegime";
        }
        memberService.saveNewCaporegime((Long) session.getAttribute("clan_id"), caporegimeInfoSample);

        return "redirect:/authority/members";
    }

    @GetMapping("/members/add-soldier")
    public String showAddSoldierPage(Model model) {
        model.addAttribute("soldier", new RegisterNewSoldierInfoSample());
        return "/authority/add_soldier";
    }

    @PostMapping("/members/add-soldier")
    public String registerSoldier(@Valid @ModelAttribute("soldier") RegisterNewSoldierInfoSample soldierInfoSample,
                                  BindingResult bindingResult,
                                  Model model,
                                  HttpSession session) {
        System.out.println("------------------------------->>>" + soldierInfoSample.getSpecialization());

        if (bindingResult.hasErrors()) {
            return "/authority/add_soldier";
        }
        if (userService.isAccountUserExists(soldierInfoSample.getLogin())) {
            model.addAttribute("existsLogin", "error.login_exists");
            return "/authority/add_soldier";
        }
        memberService.saveNewSoldier((Long) session.getAttribute("clan_id"), soldierInfoSample);

        return "redirect:/authority/members";
    }
}
