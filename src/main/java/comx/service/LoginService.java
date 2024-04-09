package comx.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import comx.entity.Login;
import comx.repository.LoginRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class LoginService implements UserDetailsService{

    @Autowired
    LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> result = loginRepository.findUserByName(username);
        if(result.isPresent()) {
            Login ll  = result.get();
            return User.builder().username(ll.getUsername()).password(ll.getPassword()).roles(ll.getRole()?"ADMIN":"USER").build();
        }else {
            throw new UsernameNotFoundException(username);
        }

    }
    @Transactional // Ensure the transaction is managed by Spring

    public String createAccount(Login ll, Model model) {
        Optional<Login> result = loginRepository.findUserByName(ll.getUsername());
        if (result.isPresent()) {
            model.addAttribute("error","email not available");
            return "signup"; // Return signup view with error message
        } else {
            loginRepository.save(ll);
            return "index"; // Redirect to login page after successful signup
        }

    }

}
