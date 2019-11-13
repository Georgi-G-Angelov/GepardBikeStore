package bikestore.web;

import bikestore.entities.Bike;
import bikestore.entities.Part;
import bikestore.services.base.BikesService;
import bikestore.services.base.PartsService;
import bikestore.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final BikesService bikesService;
    private final PartsService partsService;
    private final UsersService usersService;

    @Autowired
    public HomeController(BikesService bikesService, PartsService partsService, UsersService usersService) {
        this.bikesService = bikesService;
        this.partsService = partsService;
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Bike> bikes = bikesService.getAllBikes();
        List<Part> parts = partsService.getAllParts();
        model.addAttribute("message", "Hello!");
        model.addAttribute("bikes", bikes);
        model.addAttribute("parts", parts);
        return "index";
    }

}
