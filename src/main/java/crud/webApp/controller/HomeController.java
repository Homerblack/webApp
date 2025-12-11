package crud.webApp.controller;


import crud.webApp.entity.UserInfoEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //displaying the form on the page
    @GetMapping("/")
    public String ShowForm() {
        return "home";
    }
}
