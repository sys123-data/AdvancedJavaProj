package comx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("shop")
public class UserController {

    @GetMapping(value = "")
    public String open() {
        return "shop";
    }
}
