package by.itacademy.controllers;

import by.itacademy.entity.Authority;
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
@RequestMapping("/authority")
public class AuthorityController {

    private final MemberService memberService;

    @Autowired
    public AuthorityController(MemberService memberService) {
        this.memberService = memberService;
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
    public String showAddAuthorityPage() {
        return "/authority/add_authority";
    }

    @GetMapping("/members/add-caporegime")
    public String showAddCaporegimePage() {
        return "/authority/add_caporegime";
    }

    @GetMapping("/members/add-soldier")
    public String showAddSoldierPage() {
        return "/authority/add_soldier";
    }
}
