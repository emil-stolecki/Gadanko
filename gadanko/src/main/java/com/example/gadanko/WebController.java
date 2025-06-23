package com.example.gadanko;

import com.example.gadanko.objects.LoginData;
import com.example.gadanko.objects.RegisterData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {


    @GetMapping("/")
    public String start(Model model) {

        model.addAttribute("registerData",new RegisterData());
        model.addAttribute("loginData", new LoginData());
        return "index";
    }

    @PostMapping("/register")
    public String registerUser( Model model, @ModelAttribute RegisterData registerData, BindingResult bindingResult){
        System.out.println(bindingResult.hasErrors());
        System.out.println(registerData.getUsername());
        model.addAttribute("registerData",registerData);
        return "profile";
    }

    @PostMapping("/login")
    public String loginUser( Model model, @ModelAttribute LoginData loginData){
        System.out.println(loginData.getUsername());
        return "profile";
    }

    @GetMapping("/home")
    public String home() {
        //check if logged in
        return "home";
    }

    @GetMapping("/chat/id")
    public String getChat() {
        //check if logged in
        //get chat data
        return "chat";
    }





    @GetMapping("/profile")
    public String profile(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "profile";
    }

}
