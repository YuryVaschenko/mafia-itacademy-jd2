package by.itacademy.controllers;

import by.itacademy.dto.RegisterNewDebtorInfoSample;
import by.itacademy.services.DebtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Yury V. on 18.07.17.
 */

@Controller
public class CommonController {

    private final DebtorService debtorService;

    @Autowired
    public CommonController(DebtorService debtorService) {
        this.debtorService = debtorService;
    }

    @ModelAttribute("debtorSample")
    public RegisterNewDebtorInfoSample debtorSample() {
        return new RegisterNewDebtorInfoSample();
    }

    @GetMapping("/debtor/add")
    public String showAddDebtorPage() {
        return "add_debtor";
    }

    @PostMapping("/debtor/add")
    public String addNewDebtor(RegisterNewDebtorInfoSample sample) {
        debtorService.registerNewDebtor(sample);
        return "redirect: /redirect";
    }
}
