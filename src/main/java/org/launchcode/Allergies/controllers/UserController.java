package org.launchcode.Allergies.controllers;

import org.launchcode.Allergies.models.User;
import org.launchcode.Allergies.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "Been There");
        model.addAttribute("user", userDao.findAll());
        return "user/index";
    }

    @RequestMapping(value="register", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Add User");
        return "user/register";
    }

    @RequestMapping(value="register", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "user/register";
        }
        else {
            userDao.save(user);
            return "redirect:/user";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String userLogin(Model model, User user) {
        model.addAttribute("name", user.getName());
        return "user/login";
    }

    @RequestMapping(value="login", method = RequestMethod.POST)
    public String userLogin(Model model, @ModelAttribute @Valid Errors errors) {
        // User myfounduser = userDao.findbyusername(username)
        // if password.equals(myfound.getpassword())
        if (errors.hasErrors()) {
            return "user/login";
        }
//        } else {
//            userDao.findByEmail(User.email);
//            return "redirect:/user";
//        }
        return "redirect:welcome";
    }

//    //query the dB based on the username
//    //@RequestParam
//    @RequestMapping(value="login", method = RequestMethod.POST)
//    public String userLogin(Model model,  Errors errors) {
//      // User myfounduser = userDao.findbyusername(username)
//        // if password.equals(myfound.getpassword())
//            return "user/login";
//       //   return "redirect:/user/welcome";
//
//    }

    @RequestMapping(value = "welcome", method = RequestMethod.POST)
    public String success(Model model, User user) {
        model.addAttribute("title", "Welcome");
        return "user/welcome";
    }


}
