package in.niteshramola.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");

        model.addAttribute("title", "Home");
        model.addAttribute("name", "Nitesh Ramola");
        return "home";
    }

}
