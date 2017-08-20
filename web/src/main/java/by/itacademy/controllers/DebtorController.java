package by.itacademy.controllers;

import by.itacademy.dto.RegisterNewDebtorInfoSample;
import by.itacademy.entity.Debtor;
import by.itacademy.entity.Member;
import by.itacademy.services.DebtorService;
import by.itacademy.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Yury V. on 18.07.17.
 */

@Controller
public class DebtorController {

    private final DebtorService debtorService;
    private final MemberService memberService;

    @Autowired
    public DebtorController(DebtorService debtorService, MemberService memberService) {
        this.debtorService = debtorService;
        this.memberService = memberService;
    }

    @ModelAttribute("debtorSample")
    public RegisterNewDebtorInfoSample debtorSample() {
        return new RegisterNewDebtorInfoSample();
    }

    @GetMapping("/debtors")
    public String showDebtorsPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @RequestParam(value = "count", required = false, defaultValue = "10") int count,
                                  Model model, HttpSession httpSession) {

        Long clanId = (Long) httpSession.getAttribute("clan_id");

        int debtorsCount = debtorService.getDebtorsCount(clanId);

        int maxPages = debtorsCount / count;
        if (debtorsCount % count != 0) {
            maxPages += 1;
        }

        List<Debtor> listOfDebtors = debtorService.getPaginatedListOfDebtors(clanId, (page - 1) * count, count);
        model.addAttribute("listOfDebtors", listOfDebtors);
        model.addAttribute("currentPage", page);
        model.addAttribute("maxPages", maxPages);
        model.addAttribute("count", count);
        return "debtors";
    }

    @GetMapping("/debtors/add")
    public String showAddDebtorPage() {
        return "add_debtor";
    }

    @PostMapping("/debtors/add")
    public String addNewDebtor(RegisterNewDebtorInfoSample sample) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.findMemberByLogin(user.getUsername());
        debtorService.registerNewDebtor(sample, member.getClan());
        return "redirect: /redirect";
    }
}
