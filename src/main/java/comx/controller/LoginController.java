package comx.controller;

import comx.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import comx.entity.Login;
import comx.service.LoginService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    // http://localhost:8080/register , method : post

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model, Login login) {
        if ("true".equals(error)) {
            model.addAttribute("error", true); // Add error attribute if error parameter is present
        }
        return "index";
    }
    @GetMapping(value = "signup")
    public String signUpPage(Login login,Model model) {
        model.addAttribute("login", login);
        return "signup";
    }
    @PostMapping(value = "signupindb")
    public String signUpIntoDb(Login login, Model model) {
        if (login.getPassword().length() > 2 && !login.getPassword().equals(login.getUsername()) && EmailValidator.isValidEmail(login.getUsername())) {
            login.setPassword(passwordEncoder.encode(login.getPassword()));
            String result = loginService.createAccount(login, model);
            System.out.println(result);
            return result;
        } else {
            model.addAttribute("error1", "Password must be greater than 3 characters and not equal to email");
            return "signup";
        }
    }


}

