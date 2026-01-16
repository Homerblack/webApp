package crud.webApp.controller;


import crud.webApp.entity.RoomUserInfoEntity;
import crud.webApp.entity.UserIdPasswordEntity;
import crud.webApp.service.RoomUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class RoomUserController {



    /// Creating Constructor
    private final RoomUserService roomUserService;


    public RoomUserController(RoomUserService roomUserService) {
        this.roomUserService = roomUserService;
    }


    /// This is for showing the room registration form
    @GetMapping("/roomUserForm")
    public String roomUserFormDisplay(Model model) {
        model.addAttribute("roomUser", new RoomUserInfoEntity());
        return "roomUserForm" ;
    }


    /// This is to insert or save room users
    @PostMapping("/saveRoomUser")
    public String saveRoomUserForm(@ModelAttribute RoomUserInfoEntity roomUser, RedirectAttributes redirectAttributes) {
        roomUserService.saveRoomUser(roomUser);
        redirectAttributes.addFlashAttribute("message", "Room User has been saved successfully");
        return "redirect:/roomUserForm" ;
    }


    /// User Display if registered

    @GetMapping("/userLogin")
    public String userLoginDisplay(Model model) {
        model.addAttribute("roomUser", new RoomUserInfoEntity());

        return "RentUserLogin" ;
    }


    // Process login (POST)
    @PostMapping("/login")
    public String processLogin(
            // get ID from form
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {


        // Call service to check credentials
        Optional<RoomUserInfoEntity> isUser = roomUserService.getUserById(email, password);

        if (isUser.isPresent()) {
            // Login SUCCESS → show user details
            RoomUserInfoEntity user = isUser.get();
            model.addAttribute("user", user);
            session.setAttribute("loggedInUser", user);
            return "/dashboard";

             // optional flag
        } else {
            // Login FAILED → show error
            model.addAttribute("email", email);
            model.addAttribute("error", "Invalid ID or Password");
        }

        // Always return the same page
        model.addAttribute("roomUser", new UserIdPasswordEntity()); // for form binding
        return "RentUserLogin";
    }



}
