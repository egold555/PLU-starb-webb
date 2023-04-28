package webb.server.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import webb.server.service.UserService;

import java.io.IOException;
import java.security.Principal;

@Component
public class AdminFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    private static class RequestWrapper extends HttpServletRequestWrapper {
        private Principal principal;

        private class UserPrincipal implements Principal {
            private String name;

            public UserPrincipal( String uname ) {
                name = uname;
            }

            @Override
            public String getName() {
                return name;
            }
        }

        public RequestWrapper(HttpServletRequest request, String username) {
            super(request);
            this.principal = new UserPrincipal(username);
        }

        @Override
        public Principal getUserPrincipal() {
            return principal;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        boolean authorized = false;

        // Allow only GET requests to pass through without authorization
        if(request.getMethod().equals("GET")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Grab Authorization header value from request header
        String username = request.getHeader("Authorization");
        if( username != null && username.length() > 0 ) {
            authorized = this.userService.isAdmin(username);
        }

        if( authorized ) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }

}
