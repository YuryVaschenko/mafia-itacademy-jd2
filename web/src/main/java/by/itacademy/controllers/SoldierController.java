package by.itacademy.controllers;

import by.itacademy.entity.Soldier;
import by.itacademy.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Yury V. on 15.07.17.
 */

@Controller
@RequestMapping("/soldier")
public class SoldierController {

    private final MemberService memberService;

    @Autowired
    public SoldierController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String showStartSoldierPage() {
        return "redirect: /soldier/profile";
    }

    @GetMapping("/affairs")
    public String showAffairsPage() {
        return "affairs";
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Soldier soldier = (Soldier) memberService.findMemberByLogin(user.getUsername());
        model.addAttribute("soldier", soldier);
        return "profile";
    }

}
