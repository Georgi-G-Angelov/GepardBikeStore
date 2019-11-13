package bikestore.web;

import bikestore.entities.Part;
import bikestore.entities.PartCart;
import bikestore.services.base.PartCartsService;
import bikestore.services.base.PartsService;
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
@RequestMapping("/parts")
public class PartsController {
    private final PartsService partsService;
    private final PartCartsService partCartsService;
    private final UsersService usersService;
    private final LoggerProvider<PartsController> logger;

    @Autowired
    public PartsController(PartsService partsService,
                           PartCartsService partCartsService, UsersService usersService,
                           LoggerProvider<PartsController> logger) {
        this.partsService = partsService;
        this.partCartsService = partCartsService;
        this.usersService = usersService;
        this.logger = logger;
        this.logger.setClass(PartsController.class);
    }

    @GetMapping("")
    public String allParts(Model model, @ModelAttribute String action){
        List<Part> parts = partsService.getAllParts();
        action = "/parts";
        model.addAttribute("searched",new Part());
        model.addAttribute("message", "Parts:");
        model.addAttribute("parts", parts);
        model.addAttribute("action", action);
        return "parts/parts";
    }

    @GetMapping("/bycategory/{category}")
    public String partsByCategory(@PathVariable String category, Model model, @ModelAttribute String action){
        List<Part> parts = partsService.getPartsByCategory(category);
        action = "/parts/bycategory/" + category;
        model.addAttribute("message", "Parts with category " + category + " :");
        model.addAttribute("parts", parts);
        model.addAttribute("searched",new Part());
        model.addAttribute("action", action);
        return "parts/parts";
    }

    @PostMapping("/bycategory/{category}")
    public String searchByCategoryAndName(
            @ModelAttribute Part searched, Model model,@PathVariable String category)
    {
        List<Part> found = new ArrayList<>();
        List<Part> all = partsService.getPartsByCategory(category);
        for(Part part : all){
            if(part.getName().toLowerCase().contains(searched.getName().toLowerCase())){
                found.add(part);
            }
        }
        String action = "/parts/bycategory/" + category;
        model.addAttribute("parts", found);
        model.addAttribute("message", "Parts:");
        model.addAttribute("searched",new Part());
        model.addAttribute("action",action);
        return "parts/parts";
    }

    @PostMapping("")
    public String search(
            @ModelAttribute Part searched, Model model)
    {
        List<Part> found = new ArrayList<>();
        List<Part> all = partsService.getAllParts();
        for(Part part : all){
            if(part.getName().toLowerCase().contains(searched.getName().toLowerCase())){
                found.add(part);
            }
        }
        String action = "/parts";
        model.addAttribute("parts", found);
        model.addAttribute("message", "Parts:");
        model.addAttribute("searched",new Part());
        model.addAttribute("action",action);

        return "parts/parts";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        logger.info("In part details");

        Part part = partsService.getPartById(Integer.parseInt(id));
        model.addAttribute("part", part);

        return "parts/details";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCartById(@PathVariable int id,
                                @ModelAttribute(value = "partCart") PartCart partCart,
                                Principal principal,
                                Model model
    ) {
        model.addAttribute("partCart",partCart);
        User user = usersService.getUserByUsername(principal.getName());
        partCart.setUserId(user.getId());
        partCart.setPartId(id);
        partCartsService.createPartCart(partCart);
        return "redirect:/parts";
    }

    @GetMapping("/bycategory/parts/addToCart/{id}")
    public String addToCartByIdAndCategory(@PathVariable int id,
                                @ModelAttribute(value = "partCart") PartCart partCart,
                                Principal principal,
                                Model model
    ) {
        model.addAttribute("partCart",partCart);
        User user = usersService.getUserByUsername(principal.getName());
        partCart.setUserId(user.getId());
        partCart.setPartId(id);
        partCartsService.createPartCart(partCart);
        return "redirect:/parts";
    }

}