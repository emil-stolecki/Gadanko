package com.example.gadanko;

import com.example.gadanko.objects.models.Group;
import com.example.gadanko.objects.Message;
import com.example.gadanko.objects.form.LoginData;
import com.example.gadanko.objects.form.RegisterData;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.random.RandomGenerator;

@Controller
public class WebController {


    @GetMapping("/")
    public String start(Model model) {

        model.addAttribute("registerData",new RegisterData());
        model.addAttribute("loginData", new LoginData());

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
    public String home(Model model) {
        //check if logged in
        //get group list from db
        Group[] group_list = new Group[6];
        group_list[0]= new Group(0L,"General");
        group_list[1]= new Group(1L,"Art");
        group_list[2]= new Group(2L,"Politics");
        group_list[3]= new Group(5L,"Tech");
        group_list[4]= new Group(7L,"Fashion");
        group_list[5]= new Group(9L,"Travel");

        model.addAttribute("groups",group_list);

        return "home";
    }

    @GetMapping("/chat/{id}")
    public String getChat(@PathVariable Long id, Model model) {
        //check if logged in
        //get chat data
        Message[] mock_messages = new Message[10];
        mock_messages[0]=new Message("user1", "20:06 22-06-2025","AAAA");
        mock_messages[1]=new Message("user2", "20:06 22-06-2025", "UWU");
        mock_messages[2]=new Message("user1", "20:06 22-06-2025","OwO");
        mock_messages[3]=new Message("user3", "20:06 22-06-2025","LONG TEXT LONG TEXT LONG TEXT LONG TEXT LONG TEXT LONG TEXT LONG TEXT LONG TEXT LONG TEXT LONG TEXT LONG TEXT LONG TEXT LONG TEXT LONG TEXT");
        mock_messages[4]=new Message("user1", "20:06 22-06-2025",",XDDDDDDD");
        mock_messages[5]=new Message("user2", "20:06 22-06-2025","ok");
        mock_messages[6]=new Message("user3", "20:06 22-06-2025","kys");
        mock_messages[7]=new Message("user1", "20:06 22-06-2025","no u");
        mock_messages[8]=new Message("user2", "20:06 22-06-2025","ur mom");
        mock_messages[9]=new Message("user3", "20:06 22-06-2025","bey");

        model.addAttribute("mockchat",mock_messages);
        model.addAttribute("newMessage","");

        return "chat";
    }



}
