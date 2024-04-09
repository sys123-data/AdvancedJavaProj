package comx.controller;

import comx.model.Link;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @GetMapping(value = "")
    public String open(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails)authentication.getPrincipal()).getUsername();
        List<Link> links = new ArrayList<>();
        links.add(new Link("/admin/getProducts", "Manage Products"));
        links.add(new Link("/admin/searchUsers?email=", "Manage Users"));
        links.add(new Link("/admin/orders", "List Orders"));
        links.add(new Link("/admin/password", "Change Password"));
        links.add(new Link("/logout", "Logout"));

        model.addAttribute("links", links);
        model.addAttribute("email",email );
        return "admin";
    }



    @GetMapping(value = "orders")
    public String showOrders(
                             Model model) {

        return "orders";
    }
    @GetMapping(value = "password")
    public String showPassword(Model model){

        return "password";
    }

}

