/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Message;
import sec.project.repository.AccountRepository;
import sec.project.repository.MessageRepository;


@Controller
public class MessageController {

    @Autowired
    private HttpSession session;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;



    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public String newMessage(@RequestParam String msgContent) {
        String name = (String) session.getAttribute("logged");
        Account acc = accountRepository.findByUsername(name);
        Message msg = new Message();
        msg.setContent(msgContent);
        msg.setAccount(acc);
        messageRepository.save(msg);
        return "redirect:/chat";
    }
    
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String loadChat(Model model) {
        if (session.getAttribute("logged") != null) {
            model.addAttribute("messages", messageRepository.findAllByOrderByIdAsc());
            return "chat";
        } else {
            return "form";
        }
    }
}
