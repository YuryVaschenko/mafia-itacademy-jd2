package by.itacademy.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Yury V. on 15.07.17.
 */

@Controller
@RequestMapping("/authority")
public class AuthorityController {

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

    @GetMapping("/debtors")
    public String showDebtorsPage() {
        return "debtors";
    }

    @GetMapping("/profile")
    public String showProfilePage() {
        return "profile";
    }

    @GetMapping("/members")
    public String showMembersPage() {
        return "/authority/members";
    }
}
