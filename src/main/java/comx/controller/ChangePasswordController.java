package comx.controller;

import comx.service.NewPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "admin")
public class ChangePasswordController {
    @Autowired
    NewPassService newPassService;




    @PostMapping(value = "changePass")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @AuthenticationPrincipal UserDetails userDetails,
                                 Model model) {
        // Retrieve the username (email) of the authenticated user
        String username = userDetails.getUsername();
        String password = userDetails.getPassword();

        return newPassService.updatePassword(username,password,currentPassword,newPassword, model);

    }
}
