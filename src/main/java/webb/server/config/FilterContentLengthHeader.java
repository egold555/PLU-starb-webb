package webb.server.config;

import java.util.Arrays;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;


/**
 * This class adds the Content-Length header to every response.
 * This is required for the client to be able to parse the response correctly.
 *
 * This was copied from the following StackOverflow answer: https://stackoverflow.com/a/34305712
 */
@Configuration
public class FilterContentLengthHeader {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new ShallowEtagHeaderFilter());
        filterBean.setUrlPatterns(Arrays.asList("*"));
        return filterBean;
    }
}
