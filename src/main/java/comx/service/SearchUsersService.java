package comx.service;

import comx.entity.Login;
import comx.entity.Order;
import comx.repository.LoginRepository;
import comx.repository.OrdersRepository;
import comx.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class SearchUsersService {
    @Autowired
    private LoginRepository loginRepository;
    @Transactional
    public String getFilteredUsers(String email, Model model) {
        List<Login> result = loginRepository.findNonAdminUsersWithEmailContaining(email);
        System.out.println(result);
        if (result.isEmpty()) {
            model.addAttribute("error", "no users avalable");
        }
        else if (result.size()>0) {
            model.addAttribute("users", result);
        }
        return "users";

    }
}
