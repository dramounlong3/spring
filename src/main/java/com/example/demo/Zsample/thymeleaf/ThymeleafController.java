package com.example.demo.Zsample.thymeleaf;


import com.example.demo.Zsample.commonModel.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


// Thymeleaf要加@Controller而非@RestController
@Controller
public class ThymeleafController {


    //返回的類型一定要是String
    @GetMapping("/home")
    public String home(Model model) {
        Student student = new Student();
        student.setId(1);
        student.setName("Kyle");

        model.addAttribute("myStudent", student);

        return "index"; //返回的template name名稱, 會自動去對應resources/templates底下的檔名
    }

    @GetMapping("/hello")
    public  String hello() {
        return "hello";
    }

    //for form表單
    @PostMapping("/login")
    public String form(String userName,
                       String userPassword){
        System.out.println("userName= " + userName);
        System.out.printf("userPassword= %s", userPassword);
        return  "login";
    }
}
