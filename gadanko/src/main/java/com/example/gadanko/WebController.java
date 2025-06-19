package com.example.gadanko;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {


    @GetMapping("/")
    public String start() {
        return "index";
    }


    @GetMapping("/home")
    public String home() {
        //check if logged in
        return "mainpage";
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
