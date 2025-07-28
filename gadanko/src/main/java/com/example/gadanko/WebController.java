package com.example.gadanko;

import com.example.gadanko.objects.models.Group;
import com.example.gadanko.objects.Message;
import com.example.gadanko.objects.form.LoginData;
import com.example.gadanko.objects.form.RegisterData;
import jakarta.validation.Valid;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.util.*;
import java.util.random.RandomGenerator;

import static java.lang.Math.max;

@Controller
public class WebController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;



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

        model.addAttribute("groups",getGroups());

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

        Group[] groups = getGroups();

        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "1");
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);

        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.gadanko.objects.Message");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<Long, Message> consumer = new KafkaConsumer<>(props);
        //tmp solution
        //need better group filtering
        String topic = groups[id.intValue()-1].getName().toLowerCase();
        TopicPartition topicPartition = new TopicPartition(topic,0 );
        consumer.assign(List.of(topicPartition));

        //consumer.seekToEnd(List.of(topicPartition));
        consumer.seekToBeginning(consumer.assignment());
        //int messagesToRetrieve = 10;
        //long startIndex = max(consumer.position(topicPartition) - messagesToRetrieve,0);
        //consumer.seek(topicPartition, startIndex);
        System.out.println("HERE");
        ConsumerRecords<Long, Message> records = consumer.poll(Duration.ofMillis(100));
        records.forEach(record -> {
            if(record.value()!=null){
                System.out.println(record.value().getText());
            }
            else{
                System.out.println("nuh uh");
            }
        });

        consumer.close();

        return "chat";
    }


    @KafkaListener(topics = "general", groupId = "1")
    public void listenGroup1(Message message) {
        System.out.println("Received Message in general: " + message);
    }
    @KafkaListener(topics = "art", groupId = "1")
    public void listenGroup2(Message message) {
        System.out.println("Received Message in art: " + message);
    }
    @KafkaListener(topics = "tech", groupId = "1")
    public void listenGroup3(Message message) {
        System.out.println("Received Message in art: " + message);
    }


    private Group[] getGroups(){
        //to powinno byc wczytane raz
        Group[] group_list = new Group[6];
        group_list[0]= new Group(1L,"General");
        group_list[1]= new Group(2L,"Art");
        group_list[2]= new Group(3L,"Politics");
        group_list[3]= new Group(4L,"Tech");
        group_list[4]= new Group(5L,"Fashion");
        group_list[5]= new Group(6L,"Travel");

        return group_list;
    }

}
