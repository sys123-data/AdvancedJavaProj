package comx.controller;

import comx.service.OrderService;
import comx.service.SearchUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class SearchUsersController {
    @Autowired
    SearchUsersService searchUsersService;




    @GetMapping(value = "searchUsers")
    public String filterUsers(@RequestParam() String email,

                                 Model model) {

        return searchUsersService.getFilteredUsers(email, model);
    }


}
