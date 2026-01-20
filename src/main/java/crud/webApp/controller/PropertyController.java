package crud.webApp.controller;


import crud.webApp.dto.PropertyRegistrationDto;
import crud.webApp.entity.PropertyEntity;
import crud.webApp.entity.RoomUserInfoEntity;
import crud.webApp.service.PropertyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class PropertyController {

    /// property service
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /// Display Adding Property form
    @GetMapping("/addPropertyForm")
    public String ShowAddPropertyForm(HttpSession session, Model model) {

        RoomUserInfoEntity loggedInUser = (RoomUserInfoEntity) session.getAttribute("loggedInUser");
        PropertyRegistrationDto property = new PropertyRegistrationDto();

        // setting the defaults using setters
        property.setTitle("Cozy Single Room in Kathmandu");
        property.setPropertyType("Single Room");
        property.setRentAmount(new BigDecimal("15000"));
        property.setDepositAmount(new BigDecimal("30000"));

        property.setDistrict("Kathmandu");
        property.setCityArea("Thamel");
        property.setAddress("Near Durbar Square");

        property.setBedrooms(1);
        property.setBathrooms(1.0);
        property.setFurnishing("Semi-Furnished");
        property.setAvailableFrom(LocalDate.now().plusDays(7));

        // Pre-select some amenities (common ones in Nepal rentals)
        property.setAmenities(Arrays.asList("WiFi", "Hot Water", "Kitchen", "Parking"));

        property.setPreferredTenants("Students");
        property.setPetsAllowed(false);
        property.setSmokingAllowed(false);

        if (loggedInUser != null) {
            property.setTitle("Cozy Room by " + loggedInUser.getFirstName());
            // If you have location in user entity → use it
            // property.setDistrict(loggedInUser.getDistrict() != null ? loggedInUser.getDistrict() : "Kathmandu");
        }
        property.setDescription("We have special offer going on if you buy this month");


        System.out.println("here is your mf User ID: " + loggedInUser.getId());
        System.out.println("here is your mf User ID: " + loggedInUser.getFirstName());

        model.addAttribute("property", property);

        return "addProperty" ;
    }


    ///  Adding property
    @PostMapping("/registerProperty")
    public String RegisterProperty(@ModelAttribute PropertyRegistrationDto property,
                                   @RequestParam("images") MultipartFile[] images,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        RoomUserInfoEntity loggedInUser = (RoomUserInfoEntity) session.getAttribute("loggedInUser");

        property.setImages(images);

        propertyService.saveProperty(property, loggedInUser.getId());
        redirectAttributes.addFlashAttribute("message", "Registration successful");
        return "redirect:/addPropertyForm" ;
    }

    // This is to display all the property listed by the certain user
    @GetMapping("/my-properties")
    public String GetALlRegisteredProperty(HttpSession session, Model model) {
        RoomUserInfoEntity loggedInUser = (RoomUserInfoEntity) session.getAttribute("loggedInUser");

        // Safety check
        if (loggedInUser == null) {
            return "redirect:/login";  // or wherever your login is
        }



        List<PropertyEntity> myProperties = propertyService.getALlRegisteredProperty(loggedInUser.getId());
        System.out.println("Logged in user ID: " + loggedInUser.getId());
        System.out.println("Number of properties found: " + myProperties.size());

        if (!myProperties.isEmpty()) {
            System.out.println("First property title: " + myProperties.get(0).getTitle());
            System.out.println("First property ID: " + myProperties.get(0).getId());
        } else {
            System.out.println("No properties found for this user.");
        }
        model.addAttribute("user", loggedInUser);
        model.addAttribute("properties", myProperties);


        return "myProperties" ;
    }

}
