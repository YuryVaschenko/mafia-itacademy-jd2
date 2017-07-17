package by.itacademy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Yury V. on 15.07.17.
 */

@Controller
@RequestMapping("/soldier")
public class SoldierController {

    @GetMapping
    public String showStartSoldierPage() {
        return "redirect: /soldier/profile";
    }

    @GetMapping("/debtors")
    public String showDebtorsPage() {
        return "debtors";
    }

    @GetMapping("/affairs")
    public String showAffairsPage() {
        return "affairs";
    }

    @GetMapping("/profile")
    public String showProfilePage() {
        return "profile";
    }

}
