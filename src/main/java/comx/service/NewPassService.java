package comx.service;

import comx.entity.Login;
import comx.repository.LoginRepository;
import comx.repository.PassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class NewPassService {

    @Autowired
    private PassRepository passRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginRepository loginRepository;

    @Transactional
    public String updatePassword(String username, String currentPasswordFromDatabase,String currentPassword, String newPassword, Model model) {
        if (username==null) {
            model.addAttribute("error", "User not found");

        } else if (! isCurrentPasswordCorrect(username,currentPassword)) {
            model.addAttribute("error1", "Current Password is not correct.");
        }
        else if (currentPassword.length()<3) {
            model.addAttribute("error2", "Current Password < 3");
        }
        else if (newPassword.length()<3) {
            model.addAttribute("error3", "New Password < 3");
        }
        else if (currentPassword.equals(newPassword)) {
            model.addAttribute("error4", "New Password = Current Password");
        } else if (passwordEncoder.matches(newPassword, currentPasswordFromDatabase)) {
                model.addAttribute("error5", "You cannot use the same password as the current password.");
        }else {

            Integer optionalLogin = passRepository.updatePassword(username,passwordEncoder.encode(newPassword));
            if (optionalLogin.equals(1)) {

                model.addAttribute("success", "Password Changed");
            }else{
                model.addAttribute("error6", "Password Not Changed");
            }

        }

        return "password";
    }


    public boolean isCurrentPasswordCorrect(String username, String currentPassword) {
        // Retrieve the user from the database using their username or email
        Optional<Login> user = loginRepository.findUserByName(username); // Assuming you have a method like findByUsername in UserRepository

        if (user.isPresent()) {
            // Retrieve the password stored in the database
            String storedPassword = user.get().getPassword();

            // Compare the stored password with the current password provided by the user
            return passwordEncoder.matches(currentPassword, storedPassword);
        }
        // Return false if the user does not exist or if there's an error
        return false;
    }

}