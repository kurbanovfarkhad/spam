package com.example.spam.security.controllers;

import com.example.spam.security.services.UserService;
import com.example.spam.security.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.getAll();
        modelAndView.addObject("users", users);
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUser(user);
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/index");
        } else {
            userService.addUser(user);

            modelAndView.setViewName("redirect:/admin");
        }
        return modelAndView;
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") User user){
        userService.delete(user);
        return "redirect:/admin";
    }

}
