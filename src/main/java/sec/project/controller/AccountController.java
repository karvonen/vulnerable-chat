package sec.project.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Message;
import sec.project.repository.AccountRepository;
import sec.project.repository.MessageRepository;

@Controller
public class AccountController {

    @Autowired
    private HttpSession session;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String loadAdmin(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "admin";
    }

    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    public String loadFail() {
        return "fail";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String password) {

        Account temp = accountRepository.findByUsername(name);
        if (temp != null) {
            if (temp.getPassword().equals(password)) {
                session.setAttribute("logged", temp.getUsername());
                return "redirect:/chat";
            } else {
                return "redirect:/fail";
            }
        } else {
            accountRepository.save(new Account(name, password));
            return "done";
        }
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String changePassword(@RequestParam String name, @RequestParam String password) {
        Account acc = accountRepository.findByUsername(name);
        if (acc != null) {
            acc.setPassword(password);
            accountRepository.save(acc);
        }
        return "form";
    }

}
