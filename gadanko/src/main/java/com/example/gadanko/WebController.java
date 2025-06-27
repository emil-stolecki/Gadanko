package com.example.gadanko;

import com.example.gadanko.objects.LoginData;
import com.example.gadanko.objects.RegisterData;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {


    @GetMapping("/")
    public String start(Model model) {

        model.addAttribute("registerData",new RegisterData());
        model.addAttribute("loginData", new LoginData());
        System.out.println(model.getAttribute("registerData"));
        System.out.println(model.getAttribute("createdAccount"));
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(Model model, @Valid @ModelAttribute RegisterData registerData, BindingResult bindingResult, RedirectAttributes attributes){
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("info",true);
            attributes.addFlashAttribute("message","Invalid input");
            return "redirect:/";
        }

        //check if username and login is unique in db
        //if(registerData.getPassword().equals(registerData.getRepeat())){}
        //hash passwords

        attributes.addFlashAttribute("info",true);
        attributes.addFlashAttribute("message","Account created");
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginUser( Model model, @Valid @ModelAttribute LoginData loginData,  BindingResult bindingResult,  RedirectAttributes attributes){
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("info",true);
            attributes.addFlashAttribute("message","Incorrect Login or Password");
            return "redirect:/";
        }
        return "redirect:home";
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
