package bikestore.web;

import bikestore.entities.Bike;
import bikestore.entities.Part;
import bikestore.entities.User;
import bikestore.services.base.BikeCartsService;
import bikestore.services.base.PartCartsService;
import bikestore.services.base.UsersService;
import bikestore.utils.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UsersController {
    private final UsersService usersService;
    private final BikeCartsService bikeCartsService;
    private final PartCartsService partCartsService;

    @Autowired
    public UsersController(UsersService usersService, BikeCartsService bikeCartsService, PartCartsService partCartsService) {
        this.usersService = usersService;
        this.bikeCartsService = bikeCartsService;
        this.partCartsService = partCartsService;
    }


    @GetMapping("/auth/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String register(@ModelAttribute User user) {
        UserValidator validator = new UserValidator();
        boolean isUnique = true;
        List<User> users = usersService.getAll();
        for(User usr : users) {
            if(usr.getUsername().equals(user.getUsername())){
                isUnique = false;
            }
        }
        if(validator.isValid(user)&&isUnique==true) {
            usersService.create(user);
            return "redirect:/login";
        }
        if(validator.isValid(user)==false){
            return "redirect:/error/804";
        }
        if(isUnique==false){
            return "redirect:/error/802";
        }
        return "redirect:/auth/register";
    }

    @GetMapping("/profile")
    public String profile(
                          Model model, Principal principal) {
        User profile = usersService.getUserByUsername(principal.getName());
        String title = "My cart";
        if (principal != null) {
            User loggedInUser = usersService.getUserByUsername(principal.getName());
        }
        List<Bike> bikes = bikeCartsService.getBikesByUserId(profile.getId());
        List<Part> parts = partCartsService.getPartsByUserId(profile.getId());
        model.addAttribute("bikes",bikes);
        model.addAttribute("parts",parts);
        model.addAttribute("title", title);
        model.addAttribute("profile", profile);
        return "users/profile";
    }

    @PostMapping("/profile/checkout")
    public String checkout(
            Principal principal,
            Model model
    ) {

        User user = usersService.getUserByUsername(principal.getName());
        partCartsService.deleteByUserId(user.getId());
        bikeCartsService.deleteByUserID(user.getId());

        return "redirect:/profile";
    }
}
