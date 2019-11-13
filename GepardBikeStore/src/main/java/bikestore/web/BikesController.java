package bikestore.web;

import bikestore.entities.Bike;
import bikestore.entities.BikeCart;
import bikestore.services.base.BikeCartsService;
import bikestore.services.base.BikesService;
import bikestore.utils.loggers.base.LoggerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import bikestore.entities.User;
import bikestore.services.base.UsersService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bikes")
public class BikesController {
    private final BikesService bikesService;
    private final BikeCartsService bikeCartsService;
    private final UsersService usersService;
    private final LoggerProvider<BikesController> logger;

    @Autowired
    public BikesController(BikesService bikesService,
                           BikeCartsService bikeCartsService, UsersService usersService,
                           LoggerProvider<BikesController> logger) {
        this.bikesService = bikesService;
        this.bikeCartsService = bikeCartsService;
        this.usersService = usersService;
        this.logger = logger;
        this.logger.setClass(BikesController.class);
    }

    @GetMapping("")
    public String allBikes(Model model, @ModelAttribute String action){
        List<Bike> bikes = bikesService.getAllBikes();
        action = "/bikes";
        model.addAttribute("searched",new Bike());
        model.addAttribute("message", "Bikes:");
        model.addAttribute("bikes", bikes);
        model.addAttribute("action", action);
        return "bikes/bikes";
    }

    @GetMapping("/bycategory/{category}")
    public String bikesByCategory(@PathVariable String category, Model model, @ModelAttribute String action){
        List<Bike> bikes = bikesService.getBikesByCategory(category);
        action = "/bikes/bycategory/" + category;
        model.addAttribute("message", "Bikes with category " + category + " :");
        model.addAttribute("bikes", bikes);
        model.addAttribute("searched",new Bike());
        model.addAttribute("action", action);
        return "bikes/bikes";
    }

    @PostMapping("/bycategory/{category}")
    public String searchByCategoryAndName(
            @ModelAttribute Bike searched, Model model,@PathVariable String category)
    {
        List<Bike> found = new ArrayList<>();
        List<Bike> all = bikesService.getBikesByCategory(category);
        for(Bike bike : all){
            if(bike.getName().toLowerCase().contains(searched.getName().toLowerCase())){
                found.add(bike);
            }
        }
        String action = "/bikes/bycategory/" + category;
        model.addAttribute("bikes", found);
        model.addAttribute("message", "Bikes:");
        model.addAttribute("searched",new Bike());
        model.addAttribute("action",action);
        return "bikes/bikes";
    }

    @PostMapping("")
    public String search(
            @ModelAttribute Bike searched, Model model)
    {
        List<Bike> found = new ArrayList<>();
        List<Bike> all = bikesService.getAllBikes();
        for(Bike bike : all){
            if(bike.getName().toLowerCase().contains(searched.getName().toLowerCase())){
                found.add(bike);
            }
        }
        String action = "/bikes";
        model.addAttribute("bikes", found);
        model.addAttribute("message", "Bikes:");
        model.addAttribute("searched",new Bike());
        model.addAttribute("action",action);

        return "bikes/bikes";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        logger.info("In bike details");

        Bike bike = bikesService.getBikeById(Integer.parseInt(id));
        model.addAttribute("bike", bike);

        return "bikes/details";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCartById(@PathVariable int id,
            @ModelAttribute(value = "bikeCart") BikeCart bikeCart,
            Principal principal,
            Model model
    ) {
        model.addAttribute("bikeCart",bikeCart);
        User user = usersService.getUserByUsername(principal.getName());
        bikeCart.setUserId(user.getId());
        bikeCart.setBikeId(id);
        bikeCartsService.createBikeCart(bikeCart);
        return "redirect:/bikes";
    }

    @GetMapping("bycategory/bikes/addToCart/{id}")
    public String addToCartByIdAndCategory(@PathVariable int id,
                                @ModelAttribute(value = "bikeCart") BikeCart bikeCart,
                                Principal principal,
                                Model model
    ) {
        model.addAttribute("bikeCart",bikeCart);
        User user = usersService.getUserByUsername(principal.getName());
        bikeCart.setUserId(user.getId());
        bikeCart.setBikeId(id);
        bikeCartsService.createBikeCart(bikeCart);
        return "redirect:/bikes";
    }

}
