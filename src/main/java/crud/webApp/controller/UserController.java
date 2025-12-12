package crud.webApp.controller;


import crud.webApp.entity.UserInfoEntity;
import crud.webApp.repository.UserInfoRepository;
import crud.webApp.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {
private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;

}
    ///creating user
    //displaying the form on the page
    //blank form
    @GetMapping("/userForm")
    public String ShowForm(Model model) {
        model.addAttribute("userForm", new UserInfoEntity());
        return "userForm";
    }


    /// inserting Users
    //inserting or saving Users
    //This is for submitting the page or saving the users
    @PostMapping("/saveUser")
    public String SaveUsers(@ModelAttribute UserInfoEntity user, RedirectAttributes redirectAttributes) {
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "User has been saved successfully");
        return "redirect:/userForm";
    }



    /// Updating Users
    //This is for updating user
    //Updating Users
    @GetMapping("/updateUserList")
    public String showUpdateUserList(Model model) {
        model.addAttribute("users", userService.getActiveUsers());
        return "usersListUpdate";
    }

    /// This will return the form for Selected user
    //This is updating Form
    //updating user from the user lists
    @GetMapping("/user/update/{id}")
    public String ShowUpdatefrom(@PathVariable  long id, Model model) {
        UserInfoEntity user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("users", user);

    return "userFormForUpdate";
    }



    //Updating Users
    //This is Updating the certain user if needed
    @PostMapping("updateUser")
    public String updateUsers(@ModelAttribute UserInfoEntity user, Model model) {

        UserInfoEntity existingUser = userService.getUserById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setId(user.getId());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setConfirmPassword(user.getConfirmPassword());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setAge(user.getAge());
        existingUser.setSex(user.getSex());
        existingUser.setAddress(user.getAddress());
        existingUser.setPostalCode((user.getPostalCode()));
        existingUser.setCity(user.getCity());
        userService.saveUser(existingUser);
        model.addAttribute("message", "User has been updated successfully");
        return "redirect:/updateUserList";
    }




    //this is just for displaying all the Active Users
    //getting all the users registered in the users table
    @GetMapping("/users")
    public String ShowUsers(Model model) {
    model.addAttribute("users", userService.getAllUsers());
    return "usersList";
    }

    //This is for Deleting  user
    //Deleting  Users
    @GetMapping ("/deleteUser")
    public String DeleteUser(Model model) {
        model.addAttribute("users", userService.getActiveUsers());
        return "usersListDelete";
    }




    //This is for Deleting  user
    //Deleting  Users
    @PostMapping ("/user/delete/{id}")
    public String DeleteUserById(@PathVariable  long id, Model  model) {

    //soft deleting User By id
        userService.deleteById(id);
        model.addAttribute("users", userService.getActiveUsers());
        return "usersListDelete";
    }
}
