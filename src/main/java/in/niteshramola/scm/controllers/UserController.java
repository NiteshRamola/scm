package in.niteshramola.scm.controllers;

import in.niteshramola.scm.helpers.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    @RequestMapping("/profile")
    public String userProfile(Authentication authentication){
        String username = Helper.getEmailOfLoggedInUser(authentication);

        logger.info("username is " + username);

        return "user/profile";
    }
}
