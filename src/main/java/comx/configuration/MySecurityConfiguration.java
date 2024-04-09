package comx.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import comx.service.LoginService;



@Configuration
@EnableWebSecurity
public class MySecurityConfiguration {


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity
                    .csrf(csrf -> csrf.disable())
                    .authorizeRequests(auth -> {
                        auth.requestMatchers("/signup", "/signupindb").anonymous();
                        auth.requestMatchers("/shop/**").hasAnyRole("USER");
                        auth.requestMatchers("/admin/**").hasAnyRole("ADMIN");
                        auth.requestMatchers("/addProduct").hasAnyRole("ADMIN");
                        auth.requestMatchers("/updateProduct").hasAnyRole("ADMIN");
                        auth.requestMatchers("/getProducts").hasAnyRole("ADMIN");
                        auth.anyRequest().authenticated();
                    })
                    .formLogin(form -> form
                            .loginPage("/login")
                            .successHandler(new SuccessHandlerApp())
                            .permitAll())

                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .clearAuthentication(true) // Clear authentication on logout
                            .permitAll())

                    .build();

        }

    @Autowired
    LoginService loginService;            // it is a type of UserDetailsService

    @Bean
    public UserDetailsService userDetailService() {
        return loginService;
    }

    // it is uses to connect spring security with DAO layer
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(loginService);
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}