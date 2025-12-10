package crud.webApp.controller;


import crud.webApp.entity.UserInfoEntity;
import crud.webApp.repository.UserInfoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
private final UserInfoRepository userInfoRepository;

public UserController(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
}

    //displaying the form on the page
    @GetMapping("/user")
    public String ShowForm(Model model) {
        model.addAttribute("userForm", new UserInfoEntity());
        return "userForm";
    }

    //this is for submitting the page or saving the users
    @PostMapping("/saveUser")
    public String SaveUsers(@ModelAttribute UserInfoEntity userInfoEntity, RedirectAttributes redirectAttributes) {
        userInfoRepository.save(userInfoEntity);
        redirectAttributes.addFlashAttribute("message", "User has been saved successfully");
        return "redirect:/user";
    }



    //getting all the users registered in the users table
    @GetMapping("/users")
    public String ShowUsers(Model model) {
    model.addAttribute("users", userInfoRepository.findAll());
    return "usersList";
    }
}
