package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class LoginAndRegisterController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String login(){
        return "loginAndRegister/login";
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String registration(User user){
        return "loginAndRegister/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "loginAndRegister/registration";
        }
        userService.saveUser(user);
        model.addAttribute("success", true);
        return "redirect:/registration?success=true";
    }

    @RequestMapping("/registration/availableEmail")
    @ResponseBody
    public String availableEmail(@RequestParam String email) {
        Boolean available = userService.findUserByEmail(email) == null;
        return available.toString();
    }

    @RequestMapping("/registration/availableNick")
    @ResponseBody
    public String availableNick(@RequestParam String nick) {
        Boolean available = userService.findUserByNick(nick) == null;
        return available.toString();
    }

}
