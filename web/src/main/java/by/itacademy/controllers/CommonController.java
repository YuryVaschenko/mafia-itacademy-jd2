package by.itacademy.controllers;

import by.itacademy.dto.RegisterNewDebtorInfoSample;
import by.itacademy.entity.Member;
import by.itacademy.services.DebtorService;
import by.itacademy.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    private final MemberService memberService;

    @Autowired
    public CommonController(DebtorService debtorService, MemberService memberService) {
        this.debtorService = debtorService;
        this.memberService = memberService;
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.findMemberByLogin(user.getUsername());
        debtorService.registerNewDebtor(sample, member.getClan());
        return "redirect: /redirect";
    }
}
