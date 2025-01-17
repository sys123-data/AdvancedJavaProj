package comx.configuration;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SuccessHandlerApp extends SavedRequestAwareAuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(grant -> "ROLE_ADMIN".equals(grant.getAuthority()));
        if(isAdmin) {
            setDefaultTargetUrl("/admin");
            //response.sendRedirect("/admin");
        }else {
            setDefaultTargetUrl("/shop");
            //response.sendRedirect("/user");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
