package by.itacademy.controllers;

import by.itacademy.entity.Caporegime;
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
@RequestMapping("/caporegime")
public class CaporegimeController {

    private final MemberService memberService;

    @Autowired
    public CaporegimeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String showStartCaporegimePage() {
        return "redirect: /caporegime/profile";
    }

    @GetMapping("/affairs")
    public String showAffairsPage() {
        return "affairs";
    }

    @GetMapping("/group")
    public String showGroupPage() {
        return "/caporegime/group";
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Caporegime caporegime = (Caporegime) memberService.findMemberByLogin(user.getUsername());
        model.addAttribute("caporegime", caporegime);
        return "profile";
    }
}
