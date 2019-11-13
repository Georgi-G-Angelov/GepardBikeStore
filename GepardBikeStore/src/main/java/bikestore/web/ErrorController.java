package bikestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ErrorController {
    @Autowired
    public ErrorController(){}

    @GetMapping("/error/{code}")
    public String errorPage(@PathVariable String code, Model model){
        switch (code){
            case "801":
                model.addAttribute("message1", 801);
                model.addAttribute("message2", "Invalid solution parameters");
                model.addAttribute("message3", "/problems/" + Integer.toString(3));
                break;
            case "802":
                model.addAttribute("message1", 802);
                model.addAttribute("message2", "Username is already taken");
                model.addAttribute("message3", "/auth/register");
                break;
            case "803":
                model.addAttribute("message1", 803);
                model.addAttribute("message2", "Invalid request parameters");
                model.addAttribute("message3", "/contacts");
                break;
            case "804":
                model.addAttribute("message1", 804);
                model.addAttribute("message2", "Invalid registration parameters (you have either entered an already taken username or a too short username or password)");
                model.addAttribute("message3", "/auth/register");
                break;
            default:
                model.addAttribute("message1", 404);
                model.addAttribute("message2", "Unknown error");
                model.addAttribute("message3", "/");
        }
        return "/error/error";
    }

    @GetMapping("/error")
    public String errorPage( Model model){

        model.addAttribute("message1", 404);
        model.addAttribute("message2", "Unknown error");
        model.addAttribute("message3", "/");
        return "/error/error";
    }


}
