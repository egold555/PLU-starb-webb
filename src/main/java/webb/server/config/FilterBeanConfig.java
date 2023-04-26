package webb.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import webb.server.security.AdminFilter;
import webb.server.security.UsernameFilter;

@Configuration
public class FilterBeanConfig {

    private UsernameFilter usernameFilter;
    private AdminFilter adminFilter;

    @Autowired
    public FilterBeanConfig(UsernameFilter usernameFilter, AdminFilter adminFilter) {
        this.usernameFilter = usernameFilter;
        this.adminFilter = adminFilter;
    }

    @Bean
    public FilterRegistrationBean<UsernameFilter> securityFilterBean() {
        final FilterRegistrationBean<UsernameFilter> userReg = new FilterRegistrationBean<>(usernameFilter);

        // ************************************************
        // Configure here which paths require authentication
        userReg.addUrlPatterns("/puzzles/levels/*");
        userReg.addUrlPatterns("/puzzles/users/*");
        userReg.addUrlPatterns("/users/*");
        // ************************************************

        userReg.setOrder(1);   //defines filter execution order
        return userReg;
    }
    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilterBean() {
        final FilterRegistrationBean<AdminFilter> adminReg = new FilterRegistrationBean<>(adminFilter);

        // ************************************************
        // Configure here which paths require admin privileges
        adminReg.addUrlPatterns("/puzzles/levels/*");
        // ************************************************

        adminReg.setOrder(2);   //defines filter execution order
        return adminReg;
    }
}