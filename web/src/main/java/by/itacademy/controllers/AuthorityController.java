package by.itacademy.controllers;

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
        return "testpage";
    }

}
