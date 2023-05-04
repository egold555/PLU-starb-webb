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
import webb.server.repository.UserRepository;
import webb.server.service.UserService;

import java.io.IOException;
import java.security.Principal;

@Component
public class UsernameFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Autowired
    public UsernameFilter(UserService userService) {
        this.userService = userService;
    }
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

        boolean authenticated = false;

        // don't do anything, if the request is a POST request and from /users endpoint
        //Eric Fix: change equals to contains, because post requests have a trailing slash.
        if(request.getMethod().equals("POST") && request.getRequestURI().contains("/users")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Grab Authorization header value from request header
        String username = request.getHeader("Authorization");
        if( username != null && username.length() > 0 ) {
            authenticated = this.userService.hasAccount(username);
        }

        if( authenticated ) {
            RequestWrapper wrap = new RequestWrapper(request, username);
            filterChain.doFilter(wrap, response);
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
