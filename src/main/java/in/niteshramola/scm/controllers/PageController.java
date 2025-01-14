package in.niteshramola.scm.controllers;

import in.niteshramola.scm.entities.User;
import in.niteshramola.scm.forms.UserForm;
import in.niteshramola.scm.helpers.Message;
import in.niteshramola.scm.helpers.MessageType;
import in.niteshramola.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }


    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");

        model.addAttribute("title", "Home");
        model.addAttribute("name", "Nitesh Ramola");
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        System.out.println("About page handler");

        return "about";
    }

    @RequestMapping("/services")
    public String services() {
        System.out.println("Services page handler");

        return "services";
    }

    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();
        // userForm.setName("Nitesh");
        // userForm.setAbout("This is about : Write something about yourself");
        model.addAttribute("userForm", userForm);

        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("userForm") UserForm userForm, HttpSession session) {
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPhone(userForm.getPhone());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());
        user.setProfilePic("https://picsum.photos/200");

        userService.saveUser(user);

        Message message = Message.builder().content("Registration Successful").type(MessageType.GREEN).build();

        session.setAttribute("message", message);
        System.out.println("User saved");

        return "redirect:/register";
    }


}
