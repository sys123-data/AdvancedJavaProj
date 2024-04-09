package comx.controller;

import comx.entity.Login;
import comx.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("")
    public String redirectToHomepage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grant -> "ROLE_ADMIN".equals(grant.getAuthority()));

            if (isAdmin) {
                return "redirect:/admin"; // Redirect to admin page
            } else {
                return "redirect:/shop"; // Redirect to user page
            }
        }else{
            return "redirect:/login";
        }
    }

    @GetMapping(value = "backAdmin")
    public String backAdmin(){
        return "redirect:/admin";
    }
    @GetMapping(value = "backShop")
    public String backShop(){
        return "redirect:/shop";
    }
}

