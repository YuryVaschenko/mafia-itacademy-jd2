package by.itacademy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Yury V. on 15.07.17.
 */

@Controller
@RequestMapping("/caporegime")
public class CaporegimeController {

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

    @GetMapping("/debtors")
    public String showDebtorsPage() {
        return "debtors";
    }

    @GetMapping("/profile")
    public String showProfilePage() {
        return "profile";
    }
}
