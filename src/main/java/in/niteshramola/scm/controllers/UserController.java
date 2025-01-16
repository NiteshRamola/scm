package in.niteshramola.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    @RequestMapping("/profile")
    public String userProfile(){
        return "user/profile";
    }
}
