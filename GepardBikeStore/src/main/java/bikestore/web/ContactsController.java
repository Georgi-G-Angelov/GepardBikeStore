package bikestore.web;

import bikestore.entities.Request;
import bikestore.services.base.RequestsService;
import bikestore.utils.validators.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactsController {
    private final RequestsService requestsService;

    @Autowired
    public ContactsController(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    @GetMapping("")
    public String contacts(Model model){
        model.addAttribute("request",new Request());
        return "contacts";
    }

    @PostMapping("/sendRequest")
    public String sendRequest(@ModelAttribute Request request) {

        RequestValidator validator = new RequestValidator();
        if(validator.isValid(request)) {
            requestsService.createRequest(request);
            return "redirect:/contacts";
        } else {
            return "redirect:/error/803";
        }
    }
}
